package com.techkingsley.agromall.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.techkingsley.agromall.R
import com.techkingsley.agromall.data.Farmers
import com.techkingsley.agromall.databinding.DashboardFragmentBinding

class DashboardFragment : Fragment(R.layout.dashboard_fragment), FarmersRecyclerAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = DashboardFragment()
        private const val TAG = "DashboardFragment"
    }

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var viewBinding: DashboardFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding = DashboardFragmentBinding.bind(requireView())

        viewBinding.lifecycleOwner = this
        viewModel.observeFarmers.observe(this.viewLifecycleOwner, Observer {
            setUpAdapter(it)
        })

        viewModel.getTotalFarmers.observe(this.viewLifecycleOwner, Observer {
            it?.let {
                viewBinding.textNumberOfFarmers.text = "$it"
            }
        })

        viewModel.getTotalFarms.observe(this.viewLifecycleOwner, Observer {
            it?.let {
                viewBinding.textNumberOfFarms.text = "$it"
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboard_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.registerFarmer) {
            val bundle = Bundle()
            bundle.putBoolean("FromDashBoard", true)
            findNavController().navigate(R.id.action_dashboardFragment_to_registrationFragment, bundle)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpAdapter(farmers: List<Farmers>) {
        val farmersAdapter = FarmersRecyclerAdapter()
        farmersAdapter.submitList(farmers)
        farmersAdapter.setOnItemClickListener(this)
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        viewBinding.farmersRecyclerView.addItemDecoration(dividerItemDecoration)
        viewBinding.farmersRecyclerView.adapter = farmersAdapter
    }

    override fun onAddFarmClicked(farmer: Farmers) {
        Log.i(TAG, "clicked farmer is $farmer")
        val farmerId = farmer.id
        val action = DashboardFragmentDirections.actionDashboardFragmentToAddFarmFragment(farmerId)
        findNavController().navigate(action)
    }

    override fun onViewAllFarmsClicked(farmer: Farmers) {
        Log.i(TAG, "clicked farmer is $farmer")
        val action = DashboardFragmentDirections.actionDashboardFragmentToFarmsFragment(farmer)
        findNavController().navigate(action)
    }

}
