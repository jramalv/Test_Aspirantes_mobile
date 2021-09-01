package com.example.test_aspirantes_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Setting (
    @SerialName("is_special_prices")
    val is_special_prices:String ="",

    @SerialName("type_food_sales")
    val type_food_sales:String ="",

    @SerialName("cs_merchant_id")
    val cs_merchant_id:String ="",

    @SerialName("vco_merchant_id")
    val vco_merchant_id:String =""
): java.io.Serializable

