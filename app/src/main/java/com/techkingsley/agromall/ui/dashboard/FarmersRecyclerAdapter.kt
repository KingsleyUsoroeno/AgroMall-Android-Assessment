package com.techkingsley.agromall.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techkingsley.agromall.R
import com.techkingsley.agromall.data.Farmers
import com.techkingsley.agromall.databinding.LayoutFarmersBinding

class FarmersRecyclerAdapter : ListAdapter<Farmers, FarmersRecyclerAdapter.FarmViewHolder>(FarmersDiffUtil()) {

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onAddFarmClicked(farmer: Farmers)
        fun onViewAllFarmsClicked(farmer: Farmers)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private lateinit var viewBinding: LayoutFarmersBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmViewHolder {
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_farmers, parent, false)
        return FarmViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: FarmViewHolder, position: Int) {
        val notification = getItem(position)
        holder.bind(notification)
    }

    inner class FarmViewHolder(view: LayoutFarmersBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(farmer: Farmers) {
            viewBinding.farmers = farmer
            //Log.i("farmsAdapter", "${getAllFarmsByFarmerId(farmer.id)}")
            viewBinding.executePendingBindings()
            viewBinding.addFarmBtn.setOnClickListener {
                listener.onAddFarmClicked(farmer)
            }

            viewBinding.viewFarmsBtn.setOnClickListener {
                listener.onViewAllFarmsClicked(farmer)
            }
        }
    }

    class FarmersDiffUtil : DiffUtil.ItemCallback<Farmers>() {
        override fun areItemsTheSame(oldItem: Farmers, newItem: Farmers): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Farmers, newItem: Farmers): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            if (imageUrl != null) {
                Glide.with(view.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.user_placeholder_image)
                    .into(view)
            } else {
                Glide.with(view.context)
                    .load(R.drawable.user_placeholder_image)
                    .into(view)
            }
        }
    }
}