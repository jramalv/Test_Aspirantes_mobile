package com.example.test_aspirantes_mobile.model.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Size(
    @SerialName("large")
    val large:String = "",

    @SerialName("medium")
    val medium:String = "",

    @SerialName("small")
    val small:String = "",

    @SerialName("x-large")
    val xLarge:String = ""

): java.io.Serializable