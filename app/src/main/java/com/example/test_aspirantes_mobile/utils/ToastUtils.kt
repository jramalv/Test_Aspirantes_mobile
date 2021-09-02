package com.example.test_aspirantes_mobile.utils

import android.content.Context
import android.widget.Toast
import com.example.test_aspirantes_mobile.model.models.ErrorResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject

class ToastUtils {
    companion object{
        fun showErrorToast(context: Context,message: String){
            Toast.makeText(context, message,Toast.LENGTH_SHORT).show()
        }

        fun showErrorToastFromJson(context: Context, response:String){
            Toast.makeText(context, Json{ ignoreUnknownKeys = true }.decodeFromString<ErrorResponse>(
                JSONObject(response).toString())
                .error_description, Toast.LENGTH_SHORT).show()
        }
    }
}