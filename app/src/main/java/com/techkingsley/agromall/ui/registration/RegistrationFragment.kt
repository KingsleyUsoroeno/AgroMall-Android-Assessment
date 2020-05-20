package com.techkingsley.agromall.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.techkingsley.agromall.R
import kotlinx.android.synthetic.main.registration_fragment.*

class RegistrationFragment : Fragment(R.layout.registration_fragment) {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
        submitBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_registrationFragment_to_dashboardFragment)
        }
    }
}
