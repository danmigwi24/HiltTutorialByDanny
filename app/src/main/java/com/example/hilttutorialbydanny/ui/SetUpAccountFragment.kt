package com.example.hilttutorialbydanny.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hilttutorialbydanny.R
import com.example.hilttutorialbydanny.data.UserPreferences
import com.example.hilttutorialbydanny.databinding.FragmentSetUpAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SetUpAccountFragment : Fragment(R.layout.fragment_set_up_account) {
    private val args: SetUpAccountFragmentArgs by navArgs()
    private var _binding: FragmentSetUpAccountBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSetUpAccountBinding.bind(view)


        userPreferences.authToken.asLiveData().observe(viewLifecycleOwner, Observer {
            binding.textViewToken2.text = "Token2 :\n${it ?: "Token is null"}"
        })

        binding.textView.text = "Token :\n${args.loginResponse.token}"
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_setUpAccountFragment_to_loginFragment)
        }
    }

}