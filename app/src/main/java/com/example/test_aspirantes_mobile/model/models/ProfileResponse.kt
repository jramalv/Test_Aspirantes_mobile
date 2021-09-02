package com.example.test_aspirantes_mobile.model.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse (
    @SerialName("email")
    val email:String = "",

    @SerialName("first_name")
    val first_name:String = "",

    @SerialName("last_name")
    val last_name:String = "",

    @SerialName("phone_number")
    val phone_number:String = "",

    @SerialName("profile_picture")
    val profile_picture:String ="",

    @SerialName("card_number")
    val card_number:String =""
): java.io.Serializable

