package com.example.test_aspirantes_mobile.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.test_aspirantes_mobile.R
import com.example.test_aspirantes_mobile.R.id.action_menu_billboard
import com.example.test_aspirantes_mobile.R.id.action_menu_profile
import com.example.test_aspirantes_mobile.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mBottomNavigator: BottomNavigationView
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mNavController = findNavController(R.id.activity_main_fg_container)
        mBottomNavigator = binding.activityMainBottomNav
        mBottomNavigator.setupWithNavController(mNavController)
        mBottomNavigator.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                action_menu_billboard->{
                    mNavController.navigate(R.id.action_ProfileFragment_to_BillBoardFragment)
                }
                action_menu_profile->{
                    mNavController.navigate(R.id.action_BillBoardFragment_to_ProfileFragment)
                }
            }
            true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


}