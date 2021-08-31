package com.example.test_aspirantes_mobile.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test_aspirantes_mobile.databinding.ActivityDetailBillboardBinding

class DetailBillBoardActivity: AppCompatActivity() {

    private lateinit var binding:ActivityDetailBillboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBillboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}