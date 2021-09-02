package com.example.test_aspirantes_mobile.views.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_aspirantes_mobile.databinding.FragmentProfileBinding
import com.example.test_aspirantes_mobile.model.models.ProfileResponse
import com.example.test_aspirantes_mobile.model.viewmodel.BillBoardViewModel
import com.example.test_aspirantes_mobile.utils.Constants
import com.example.test_aspirantes_mobile.utils.Utils
import com.example.test_aspirantes_mobile.views.MyApplication
import com.example.test_aspirantes_mobile.views.activity.LoginActvity
import com.squareup.picasso.Picasso
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FragmentProfile : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var pref:SharedPreferences

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = MyApplication.appContext!!.getSharedPreferences(Constants.CINEMAS,0)
        bindProfile(Json.decodeFromString(pref.getString(Constants.PROFILE,"").toString()))
    }


    private fun bindProfile(profileResponse: ProfileResponse){
        binding.fragmentProfileTvUserName.text = (profileResponse.first_name+" "
                +profileResponse.last_name)
        if(profileResponse.profile_picture.isNotEmpty()){
            Utils.loadImage(MyApplication.appContext!!,binding.fragmentProfileCivUserProfile,
                profileResponse.profile_picture)
        }
        if(profileResponse.phone_number.contentEquals("")){
            binding.fragmentProfileTvPhone.visibility = View.VISIBLE
            binding.fragmentProfileTvPhone.text = profileResponse.phone_number
        }else{
            binding.fragmentProfileTvPhone.visibility = View.GONE
        }
        binding.fargmentProfileTvEmail.text = profileResponse.email
        binding.fragmentProfileIvExit.setOnClickListener{
            clearAllSaveData()
            goToLogin()
        }
    }



    private fun clearAllSaveData(){
        MyApplication.appContext!!.getSharedPreferences(Constants.CINEMAS,0).edit().apply()
    }

    private fun goToLogin(){
        startActivity(Intent(requireActivity(),LoginActvity::class.java))
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}