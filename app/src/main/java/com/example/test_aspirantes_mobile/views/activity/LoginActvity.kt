package com.example.test_aspirantes_mobile.views.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.test_aspirantes_mobile.utils.Constants
import com.example.test_aspirantes_mobile.R
import com.example.test_aspirantes_mobile.databinding.ActivityLoginBinding
import com.example.test_aspirantes_mobile.model.models.BillBoardResponse
import com.example.test_aspirantes_mobile.model.models.BillBoardTable
import com.example.test_aspirantes_mobile.model.models.LoginResponse
import com.example.test_aspirantes_mobile.model.models.ProfileResponse
import com.example.test_aspirantes_mobile.model.viewmodel.BillBoardViewModel
import com.example.test_aspirantes_mobile.rest.CinemasServices
import com.example.test_aspirantes_mobile.utils.ToastUtils
import com.example.test_aspirantes_mobile.utils.Utils
import com.example.test_aspirantes_mobile.views.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoginActvity:AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginResponse: LoginResponse
    private lateinit var pref:SharedPreferences
    private lateinit var billBoardViewModel:BillBoardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = MyApplication.appContext!!.getSharedPreferences(Constants.CINEMAS,0)
        isLogged()
        loginResponse = LoginResponse()
        billBoardViewModel =
            ViewModelProvider(this).get(BillBoardViewModel::class.java)
        binding.activityLoginTieUsername.setText(Constants.DUMMY_USERNAME)
        binding.activityLoginTiePassword.setText(Constants.DUMMY_PASSWORD)
        binding.activityLoginBtnLogin.setOnClickListener{
            if(validateLoginInfo(binding)){
                if(Utils.checkWIFI(MyApplication.appContext!!)){
                    Login(binding)
                }
            }
        }
    }

    private fun isLogged(){
        if(pref.getString(Constants.TOKEN,"").isNullOrEmpty()){
            goToMain()
        }
    }


    private fun validateLoginInfo(binding: ActivityLoginBinding):Boolean{
        var valid = true
        if(binding.activityLoginTieUsername.text!!.isBlank()){
            valid = false
            binding.activityLoginTilUsername.error= MyApplication.appContext!!.resources.getString(R.string.str_username_error)
        }

        if(binding.activityLoginTiePassword.text!!.isBlank()){
            valid = false
            binding.activityLoginTilPassword.error= MyApplication.appContext!!.resources.getString(R.string.str_password_error)
        }
        return valid
    }

    private fun Login(binding: ActivityLoginBinding){
        val service = CinemasServices().loginService
        Utils.startAnimation(binding.animationLoading)
        CoroutineScope(Dispatchers.IO).launch {
            var response = service.Login(
                Constants.DUMMY_COUNTRY_CODE,
            binding.activityLoginTieUsername.text.toString(),
            binding.activityLoginTiePassword.text.toString(),
                Constants.GRANT_TYPE,
                Constants.CLIENT_ID,
                Constants.CLIENT_SECRET
            )
            withContext(Dispatchers.Main) {
                try{
                    Utils.stopAnimation(binding.animationLoading)
                    if(response.isSuccessful){
                        loginResponse = response.body()!!
                        if(loginResponse!=null){
                            Utils.saveCurrentTime(MyApplication.appContext!!, Utils.getCurrentTimeStamp())
                            saveLoginInfo(loginResponse)
                        }
                    }else{
                        ToastUtils.showErrorToastFromJson(MyApplication.appContext!!, response.errorBody()!!.string())
                    }
                }catch (e:Exception){
                    Utils.stopAnimation(binding.animationLoading)
                    ToastUtils.showErrorToast(MyApplication.appContext!!,e.message.toString())
                }
            }
        }
    }

    private fun getBillBoard(){
        val service = CinemasServices().webservice
        Utils.startAnimation(binding.animationLoading)
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                var response = service.getBillBoard(Constants.DUMMY_COUNTRY_CODE,Constants.CINEMAS)
                try{
                    Utils.stopAnimation(binding.animationLoading)
                    if(response.isSuccessful){
                        saveBillBoardResponse(response.body()!!)
                        getProfileInfo()
                    }else{
                        ToastUtils.showErrorToastFromJson(MyApplication.appContext!!,response.errorBody().toString())
                    }
                }catch (e:Exception){
                    Utils.stopAnimation(binding.animationLoading)
                    ToastUtils.showErrorToast(MyApplication.appContext!!,e.message.toString())
                }
            }
        }
    }

    //Guadamos la información de la cartelera en la base de datos
    private fun saveBillBoardResponse(response:BillBoardResponse){
        var billBoardTable = BillBoardTable(data = Json.encodeToString(response))
        billBoardViewModel.insert(billBoardTable)
    }

    private fun getProfileInfo() {
        val service = CinemasServices().webservice
        Utils.startAnimation(binding.animationLoading)
        CoroutineScope(Dispatchers.IO).launch {
            var response = service.Profile(Constants.DUMMY_COUNTRY_CODE)
            withContext(Dispatchers.Main) {
                try {
                    Utils.stopAnimation(binding.animationLoading)
                    if (response.isSuccessful) {
                            saveProfileInfo(response.body()!!)
                            goToMain()
                    }else{
                        ToastUtils.showErrorToastFromJson(MyApplication.appContext!!,
                            response.errorBody().toString())
                        goToMain()
                    }
                } catch (e: java.lang.Exception) {
                    Utils.stopAnimation(binding.animationLoading)
                    ToastUtils.showErrorToast(MyApplication.appContext!!,e.message.toString())
                    e.printStackTrace()
                }
            }
        }
    }

    //Convertimos a string nuestro modelo de profile y lo guardamos en un sharedPreferece.
    fun saveProfileInfo(profileResponse: ProfileResponse){
        MyApplication.appContext!!
            .getSharedPreferences(Constants.CINEMAS,0).edit()
            .putString(Constants.PROFILE,Json.encodeToString(profileResponse)).apply()
    }

    fun saveLoginInfo(loginResponse: LoginResponse) {
        MyApplication.appContext!!
            .getSharedPreferences(Constants.CINEMAS, 0).edit()
            .putString(Constants.ACCESS_TOKEN, loginResponse.access_token)
            .putString(Constants.TOKEN_TYPE, loginResponse.token_type)
            .putLong(Constants.EXPIRES_IN, loginResponse.expires_in)
            .putString(Constants.REFRESH_TOKEN,loginResponse.refresh_token)
            .putString(Constants.AS_CLIENT_ID,loginResponse.as_client_id)
            .putString(Constants.USERNAME,loginResponse.username)
            .putString(Constants.COUNTRY_CODE,loginResponse.country_code)
            .putString(Constants.ISSUED,loginResponse.issued)
            .putString(Constants.EXPIRES,loginResponse.expires)
            .apply()

        getBillBoard()
    }


    private fun goToMain(){
        startActivity(Intent(LoginActvity@this, MainActivity::class.java))
        finish()
    }
}