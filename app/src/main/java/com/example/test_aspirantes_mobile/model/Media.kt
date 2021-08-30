package com.example.test_aspirantes_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    @SerialName("resource")
    val resource:String="",

    @SerialName("type")
    val type:String="",

    @SerialName("code")
    val code:String=""
)