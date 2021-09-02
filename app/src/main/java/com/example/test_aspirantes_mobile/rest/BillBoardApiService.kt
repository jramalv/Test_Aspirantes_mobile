package com.example.test_aspirantes_mobile.rest

import com.example.test_aspirantes_mobile.model.models.BillBoardResponse
import com.example.test_aspirantes_mobile.model.models.CinemaResponse
import com.example.test_aspirantes_mobile.model.models.ProfileResponse
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BillBoardApiService {

    @Headers("api_key: stage_HNYh3RaK_Test")
    @GET("v1/members/profile")
    suspend fun Profile(@Query("country_code") country_code:String): Response<ProfileResponse>

    @FormUrlEncoded
    @Headers("api_key: stage_HNYh3RaK_Test")
    @GET("v2/locations/cinemas")
    suspend fun getCinemas(
        @Query("cities") cities:String,
        @Query("country_code")country_code:String,
        @Query("include_cinemas")include_cinemas:Boolean
    ):Response<ArrayList<CinemaResponse>>

    @Headers("api_key: stage_HNYh3RaK_Test")
    @GET("v2/movies")
    suspend fun getBillBoard(
        @Query("country_code") country_code:String,
        @Query("cinemas")cinemas:String
    ):Response<BillBoardResponse>
}