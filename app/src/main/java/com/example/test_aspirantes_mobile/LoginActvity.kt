package com.example.test_aspirantes_mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test_aspirantes_mobile.databinding.ActivityMainBinding

class LoginActvity:AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}