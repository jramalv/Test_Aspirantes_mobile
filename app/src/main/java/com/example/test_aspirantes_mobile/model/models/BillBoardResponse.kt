package com.example.test_aspirantes_mobile.model.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BillBoardResponse(
    @SerialName("movies")
    val movies:ArrayList<Movies>?,

    @SerialName("routes")
    val routes:ArrayList<Routes>?,
): java.io.Serializable