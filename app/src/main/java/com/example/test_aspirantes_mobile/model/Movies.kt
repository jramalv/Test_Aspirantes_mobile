package com.example.test_aspirantes_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movies(
    @SerialName("rating")
    val rating:String = "",

    @SerialName("media")
    val media:ArrayList<Media>?,

    @SerialName("cast")
    val cast:ArrayList<Cast>?,

    @SerialName("cinemas")
    val cinemas:ArrayList<String>?,

    @SerialName("position")
    val position:Int = 0,

    @SerialName("categories")
    val categories:ArrayList<String>?,

    @SerialName("genre")
    val genre:String,

    @SerialName("synopsis")
    val synopsis:String,

    @SerialName("length")
    val length:String,

    @SerialName("release_date")
    val release_date:String,

    @SerialName("distributor")
    val distributor:String,

    @SerialName("id")
    val id:Int,

    @SerialName("name")
    val name:String,

    @SerialName("code")
    val code:String,

    @SerialName("original_name")
    val original_name:String,
): java.io.Serializable
