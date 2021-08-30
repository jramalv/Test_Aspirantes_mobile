package com.example.test_aspirantes_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CinemaResponse(
    @SerialName("id")
    val id:Int = 0,

    @SerialName("vista_id")
    val vista_id:String = "",

    @SerialName("uris")
    val uris:String = "",

    @SerialName("city_id")
    val city_id:Int = 0,

    @SerialName("name")
    val name:String ="",

    @SerialName("lat")
    val lat:Double = 0.0,

    @SerialName("lng")
    val lng:Double = 0.0,

    @SerialName("phone")
    val phone:String ="",

    @SerialName("address")
    val address:String ="",

    @SerialName("position")
    val position:Int = 0,

    @SerialName("settings")
    val settings:Settings

)