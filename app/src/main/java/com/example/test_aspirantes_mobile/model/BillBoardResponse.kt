package com.example.test_aspirantes_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BillBoardResponse(
    @SerialName("movies")
    val movies:ArrayList<Movies>?
)