package com.brkcnszgn.testcoroutine

import com.google.gson.JsonObject
import com.haroldadmin.cnradapter.NetworkResponse
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

object HttpStatus{
    var code:Int? = null
}