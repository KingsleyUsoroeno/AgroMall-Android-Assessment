package com.techkingsley.agromall.ui.farms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.techkingsley.agromall.R
import com.techkingsley.agromall.data.Farms
import com.techkingsley.agromall.databinding.LayoutFarmsBinding

class FarmsRecyclerAdapter : ListAdapter<Farms, FarmsRecyclerAdapter.FarmViewHolder>(FarmersDiffUtil()) {

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onViewMapClicked(farm: Farms)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private lateinit var viewBinding: LayoutFarmsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmViewHolder {
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_farms, parent, false)
        return FarmViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: FarmViewHolder, position: Int) {
        val farms = getItem(position)
        holder.bind(farms)
    }

    inner class FarmViewHolder(view: LayoutFarmsBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(farm: Farms) {
            viewBinding.farm = farm
            viewBinding.executePendingBindings()
            viewBinding.btnViewOnMap.setOnClickListener {
                listener.onViewMapClicked(farm)
            }
        }
    }

    class FarmersDiffUtil : DiffUtil.ItemCallback<Farms>() {
        override fun areItemsTheSame(oldItem: Farms, newItem: Farms): Boolean {
            return oldItem.farmId == newItem.farmId
        }

        override fun areContentsTheSame(oldItem: Farms, newItem: Farms): Boolean {
            return oldItem == newItem
        }
    }
}