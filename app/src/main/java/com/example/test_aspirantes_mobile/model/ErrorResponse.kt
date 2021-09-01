package com.example.test_aspirantes_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("error")
    var error:String="",

    @SerialName("error_description")
    var error_description : String=""
):java.io.Serializable