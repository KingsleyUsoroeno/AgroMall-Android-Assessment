package com.techkingsley.agromall.ui.farms

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.techkingsley.agromall.R
import com.techkingsley.agromall.data.Farmers
import com.techkingsley.agromall.data.Farms
import com.techkingsley.agromall.databinding.FarmsFragmentBinding

class FarmsFragment : Fragment(R.layout.farms_fragment), FarmsRecyclerAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = FarmsFragment()
        private const val TAG = "FarmsFragment"
    }

    private val viewModel: FarmsViewModel by viewModels()
    private lateinit var viewBinding: FarmsFragmentBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding = FarmsFragmentBinding.bind(requireView())
        val safeArgs: FarmsFragmentArgs by navArgs()
        val farmer = safeArgs.farmer
        Log.i(TAG, "farmer id is ${safeArgs.farmer.id}")

        viewModel.observerFarmsByFarmerID(farmer.id, 1).observe(this.viewLifecycleOwner, Observer {
            it?.let {
                Log.i(TAG, "all farms is $it")
                setUpAdapter(it, farmer)
            }
        })
    }

    private fun setUpAdapter(farmers: List<Farms>, farmer: Farmers) {
        if (farmers.isEmpty()) {
            viewBinding.textNoFarms.text = "${farmer.fullName} has no registered farms"
            viewBinding.textNoFarms.visibility = View.VISIBLE
            viewBinding.farmsRecyclerView.visibility = View.GONE
        } else {
            viewBinding.textNoFarms.visibility = View.GONE
            viewBinding.farmsRecyclerView.visibility = View.VISIBLE
            val farmersAdapter = FarmsRecyclerAdapter()
            farmersAdapter.submitList(farmers)
            farmersAdapter.setOnItemClickListener(this)
            val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            viewBinding.farmsRecyclerView.addItemDecoration(dividerItemDecoration)
            viewBinding.farmsRecyclerView.adapter = farmersAdapter
        }
    }

    override fun onViewMapClicked(farm: Farms) {
        Log.i(TAG, "farm clicked is $farm")
        val action = FarmsFragmentDirections.actionFarmsFragmentToMapsActivity(farm)
        findNavController().navigate(action)
    }
}
