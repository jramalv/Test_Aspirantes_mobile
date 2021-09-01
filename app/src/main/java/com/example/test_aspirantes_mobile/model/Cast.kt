package com.example.test_aspirantes_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cast(
    @SerialName("label")
    val label:String = "",

    @SerialName("value")
    val value:ArrayList<String>
): java.io.Serializable