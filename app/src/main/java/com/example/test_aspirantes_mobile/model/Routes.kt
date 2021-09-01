package com.example.test_aspirantes_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Routes(
    @SerialName("code")
    val code:String = "",

    @SerialName("sizes")
    val sizes:Size?
): java.io.Serializable