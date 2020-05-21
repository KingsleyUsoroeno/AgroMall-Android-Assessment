package com.techkingsley.agromall.ui.farms

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.techkingsley.agromall.R
import com.techkingsley.agromall.databinding.AddFarmFragmentBinding

class AddFarmFragment : Fragment(R.layout.add_farm_fragment) {

    companion object {
        fun newInstance() = AddFarmFragment()
    }

    private val viewModel: AddFarmViewModel by viewModels()
    private lateinit var viewBinding: AddFarmFragmentBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs: AddFarmFragmentArgs by navArgs()
        val farmerId = safeArgs.farmerId

        viewBinding = AddFarmFragmentBinding.bind(requireView())
        viewBinding.addFarmVm = viewModel
        viewBinding.userId = 1
        viewBinding.farmerId = farmerId
        viewBinding.lifecycleOwner = this

        viewModel.successLiveData.observe(this.viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(this.requireContext(), "Farm Registered Successfully", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addFarmFragment_to_dashboardFragment)
            }
        })
    }
}
