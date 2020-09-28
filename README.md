# WallPortal
[![Kotlin Version](-](https://kotlinlang.org)
###### *NetworkResponse for Android*



 @POST("kidsvid/auth/authenticate")
    suspend fun getPerson(
        @Body  model : RequestTokenModel
    ): NetworkResponse<LoginModel, ErrorBody>



