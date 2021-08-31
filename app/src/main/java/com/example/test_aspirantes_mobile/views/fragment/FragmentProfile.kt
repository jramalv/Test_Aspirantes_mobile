package com.example.test_aspirantes_mobile.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_aspirantes_mobile.databinding.FragmentProfileBinding
import com.example.test_aspirantes_mobile.model.ProfileResponse
import com.example.test_aspirantes_mobile.rest.CinemasServices
import com.example.test_aspirantes_mobile.utils.Constants
import com.example.test_aspirantes_mobile.views.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class FragmentProfile : Fragment() {

    private var _binding: FragmentProfileBinding? = null

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
        getProfileInfo()
    }

    private fun getProfileInfo() {
        val service = CinemasServices().webservice
        CoroutineScope(Dispatchers.IO).launch {
            var response = service.Profile()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        bindProfile(response.body()!!)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun bindProfile(profileResponse: ProfileResponse){
        binding.fragmentProfileTvUserName.text = (profileResponse.first_name+" "
                +profileResponse.last_name)
        binding.fragmentProfileTvPhone.text = profileResponse.phone_number
        binding.fargmentProfileTvEmail.text = profileResponse.email
        binding.fragmentProfileTvLogout.setOnClickListener{
            clearAllSaveData()
            requireActivity().finish()
        }
    }

    private fun clearAllSaveData(){
        MyApplication.appContext!!.getSharedPreferences(Constants.CINEMAS,0)
            .edit().apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}