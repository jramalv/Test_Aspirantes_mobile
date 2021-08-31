package com.example.test_aspirantes_mobile.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.widget.Switch
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.test_aspirantes_mobile.R
import com.example.test_aspirantes_mobile.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var mNavHostFragment: NavHostFragment
    private lateinit var mNavController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mNavHostFragment = supportFragmentManager
            .findFragmentById(R.id.activity_main_fcv_container) as NavHostFragment
        mNavController = mNavHostFragment.navController
        binding.activityMainBottomNav.setupWithNavController(mNavController)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


}