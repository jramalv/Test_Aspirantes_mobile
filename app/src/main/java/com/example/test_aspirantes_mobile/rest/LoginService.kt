package com.example.test_aspirantes_mobile.rest

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.test_aspirantes_mobile.Constants
import com.example.test_aspirantes_mobile.MyApplication
import com.example.test_aspirantes_mobile.model.LoginResponse
import com.example.test_aspirantes_mobile.utils.Utils
import org.joda.time.DateTime
import org.joda.time.Minutes
import java.io.IOException

class LoginService(private val ctx: Context) {

    private var cinemas: CinemasServices? = null
    private var pref: SharedPreferences? = null
    private var u: Utils? = null


    private val sharedPreferences: SharedPreferences?
        get() {
            if (pref == null) {
                pref = ctx.getSharedPreferences(Constants.CINEMAS, 0)
            }
            return pref
        }

    private val cinemasServices: CinemasServices
        get() {
            if (cinemas == null) {
                cinemas = CinemasServices()
            }
            return cinemas!!
        }

    private val utils: Utils
        get() {
            if (u == null) {
                u = Utils()
            }
            return u!!
        }

    val isLogged: Boolean get() = sharedPreferences!!.contains(Constants.TOKEN)


    @SuppressLint("Assert")
    @Throws(IOException::class)
    internal fun getAccessToken(): String? {
        if(Utils.checkWIFI(MyApplication.appContext!!)){
            val expTime = sharedPreferences!!.getLong(Constants.CINEMAS_CURRENT_TIME, 0)
            val expires_in = sharedPreferences!!.getString(Constants.EXPIRES_IN,"").toString();
            val result = Minutes.minutesBetween(DateTime(expTime), DateTime(Utils.getCurrentTimeStamp()))
                .isGreaterThan(Minutes.minutes(Utils.getHours(expires_in)))
            if (!result) {
                var token =sharedPreferences!!.getString(Constants.TOKEN_TYPE, "")+" "+
                        sharedPreferences!!.getString(Constants.TOKEN, "")
                return token
            } else {
                Utils.saveCurrentTime(ctx, Utils.getCurrentTimeStamp())
                    val response = cinemasServices.loginService.getToken(
                        Constants.DUMMY_COUNTRY_CODE,
                        Constants.DUMMY_USERNAME,
                        Constants.DUMMY_PASSWORD,
                        Constants.GRANT_TYPE,
                        Constants.CLIENT_ID,
                        Constants.CLIENT_SECRET,
                        ).execute()
                    if (response.isSuccessful) {
                        val login = response.body()!!
                        save(login)
                        assert(login != null)
                        return login.token_type+" "+login.access_token
                    }

            }
        }
        throw IOException("Error Obtieniendo el token")
    }

    fun save(loginResponse: LoginResponse) {
        sharedPreferences!!.edit()
            .putString(Constants.ACCESS_TOKEN, loginResponse.access_token)
            .putString(Constants.TOKEN_TYPE,  loginResponse.token_type)
            .putLong(Constants.EXPIRES_IN, loginResponse.expires_in)
            .putString(Constants.REFRESH_TOKEN,loginResponse.refresh_token)
            .putString(Constants.AS_CLIENT_ID,loginResponse.as_client_id)
            .putString(Constants.USERNAME,loginResponse.username)
            .putString(Constants.COUNTRY_CODE,loginResponse.country_code)
            .putString(Constants.ISSUED,loginResponse.issued)
            .putString(Constants.EXPIRES,loginResponse.expires)
            .apply()
    }


}