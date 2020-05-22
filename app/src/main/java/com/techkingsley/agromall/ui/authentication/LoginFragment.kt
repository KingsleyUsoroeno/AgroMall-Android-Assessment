package com.techkingsley.agromall.ui.authentication

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.techkingsley.agromall.R
import com.techkingsley.agromall.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var loginBinding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hasUserLoggedIn()

        loginBinding = FragmentLoginBinding.bind(requireView())
        loginBinding.loginVM = loginViewModel
        loginBinding.lifecycleOwner = this

        loginViewModel.successLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_onBoardingFragment)
            }
        })

        loginViewModel.feedbackMessageLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    private fun hasUserLoggedIn() {
        if (loginViewModel.hasLoggedIn()) {
            findNavController().navigate(R.id.action_loginFragment_to_onBoardingFragment)
        }
    }


    companion object {
        private const val TAG = "LoginFragment"
    }
}
