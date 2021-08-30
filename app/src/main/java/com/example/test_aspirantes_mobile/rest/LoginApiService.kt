package com.example.test_aspirantes_mobile.rest

import com.example.test_aspirantes_mobile.model.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiService {

    @FormUrlEncoded
    @Headers("api_key: stage_HNYh3RaK_Test")
    @POST("v2/oauth/token")
    suspend fun Login(
        @Field("country_code")country_code:String,
        @Field("username")username:String,
        @Field("password")password:String,
        @Field("grant_type")grant_type:String,
        @Field("client_id")client_id:String,
        @Field("client_secret")client_secret:String,
    ):Response<LoginResponse>

    @FormUrlEncoded
    @Headers("api_key: stage_HNYh3RaK_Test")
    @POST("v2/oauth/token")
    fun getToken(
        @Field("country_code")country_code:String,
        @Field("username")username:String,
        @Field("password")password:String,
        @Field("grant_type")grant_type:String,
        @Field("client_id")client_id:String,
        @Field("client_secret")client_secret:String,
    ): Call<LoginResponse>

}