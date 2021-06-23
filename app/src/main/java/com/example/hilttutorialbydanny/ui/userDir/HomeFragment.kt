package com.example.hilttutorialbydanny.ui.userDir

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hilttutorialbydanny.R
import com.example.hilttutorialbydanny.data.network.Resource
import com.example.hilttutorialbydanny.databinding.FragmentHomeBinding
import com.example.hilttutorialbydanny.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        binding.progressBar2.visible(false)

        viewModel.getUser(20)

        viewModel.user.observe(viewLifecycleOwner, Observer {
            binding.progressBar2.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    binding.textView3.text = it.value.message
                    binding.textView2.text = it.value.user.firstname
                }
                is Resource.Failure -> {
                    Log.e("Failure", it.isNetWorkError.toString())
                    binding.textView3.text = it.errBody.toString()
                }
                is Resource.Loading -> {

                }
            }
        })
    }

}