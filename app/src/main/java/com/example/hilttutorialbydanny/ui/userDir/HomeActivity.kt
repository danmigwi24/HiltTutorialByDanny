package com.example.hilttutorialbydanny.ui.userDir

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.hilttutorialbydanny.data.UserPreferences
import com.example.hilttutorialbydanny.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    @Inject
    lateinit var userPreferences: UserPreferences

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences.authToken.asLiveData().observe(this, Observer {
            val message = it ?: "None"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        })
    }

}