package com.example.test_aspirantes_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(

    @SerialName("access_token")
    val access_token:String = "",

    @SerialName("token_type")
    val token_type:String = "",

    @SerialName("expires_in")
    val expires_in:Long = 0L,

    @SerialName("refresh_token")
    val refresh_token:String = "",

    @SerialName("as:client_id")
    val as_client_id:String = "",

    @SerialName("username")
    val username:String = "",

    @SerialName("country_code")
    val country_code:String = "",

    @SerialName(".issued")
    val issued:String = "",

    @SerialName(".expires")
    val expires:String = ""

):java.io.Serializable