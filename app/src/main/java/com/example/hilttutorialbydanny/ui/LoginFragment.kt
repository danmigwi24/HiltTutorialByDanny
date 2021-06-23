package com.example.hilttutorialbydanny.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hilttutorialbydanny.R
import com.example.hilttutorialbydanny.data.network.Resource
import com.example.hilttutorialbydanny.data.response.LoginResponse
import com.example.hilttutorialbydanny.data.response.User
import com.example.hilttutorialbydanny.databinding.FragmentLoginBinding
import com.example.hilttutorialbydanny.ui.userDir.HomeActivity
import com.example.hilttutorialbydanny.ui.viewModels.AuthViewModel
import com.example.hilttutorialbydanny.utils.enableButton
import com.example.hilttutorialbydanny.utils.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.progressBar.visible(false)
        binding.buttonLogin.enableButton(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    if (it.value.isSuccessful) {
                        lifecycleScope.launch {
                            viewModel.saveAuthToken(it.value.token)

                            Snackbar.make(
                                binding.root,
                                " Welcome ${it.value.user.firstname}",
                                Snackbar.LENGTH_LONG
                            )
                                .setAction("Proceed") {}.show()

//                            val direction =
//                                LoginFragmentDirections.actionLoginFragmentToSetUpAccountFragment(it.value)
//                            findNavController().navigate(direction)

                            requireActivity().startActivity(
                                Intent(
                                    requireContext(),
                                    HomeActivity::class.java
                                )
                            )
                        }

                    } else {
                        Toast.makeText(requireContext(), "Please try again", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Failure -> {
                    Snackbar.make(
                        binding.root,
                        "Please enter collect details",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Retry") {}.show()

                }
                is Resource.Loading -> {
                }
            }
        })

        binding.editTextPassword.addTextChangedListener {
            val phoneNumber = binding.editTextPhoneNumber.text.toString().trim()
            binding.buttonLogin.enableButton(phoneNumber.isNotEmpty() && it.toString().isNotEmpty())

        }
        binding.buttonSetAccount.setOnClickListener {
            val loginResponse = LoginResponse(
                true,
                "Create Account",
                "null",
                User(1, "", "", "", "", "", "", "", "", "", "")
            )
            val direction =
                LoginFragmentDirections.actionLoginFragmentToSetUpAccountFragment(loginResponse)
            findNavController().navigate(direction)
        }


        binding.buttonLogin.setOnClickListener {
            val phoneNumber = binding.editTextPhoneNumber.text.toString()
            val password = binding.editTextPassword.text.toString()
            viewModel.login(phoneNumber, password)

        }

    }

}