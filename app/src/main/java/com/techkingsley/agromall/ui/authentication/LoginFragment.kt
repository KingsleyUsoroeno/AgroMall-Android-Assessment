package com.techkingsley.agromall.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.techkingsley.agromall.R
import com.techkingsley.agromall.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var loginBinding: FragmentLoginBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginBinding = FragmentLoginBinding.bind(requireView())

        loginBinding.loginButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_onBoardingFragment)
        }
    }


    companion object {
        private const val TAG = "LoginFragment"
    }
}
