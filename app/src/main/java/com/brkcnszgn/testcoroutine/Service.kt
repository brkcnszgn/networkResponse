package com.brkcnszgn.testcoroutine

import com.brkcnszgn.networkresponse.NetworkResponse
import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {
    @POST("kidsvid/auth/authenticate")
     fun getBar(
        @Body  model : RequestTokenModel
    ): Call<JsonObject>

    @POST("kidsvid/auth/authenticate")
    suspend fun getPerson(
        @Body  model : RequestTokenModel
    ): NetworkResponse<LoginModel, ErrorBody>


}

public object HttpStatus{
    var code:Int? = null
}