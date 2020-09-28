package com.brkcnszgn.testcoroutine

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
    @SerializedName("data")
    val data: DataModel,
    @SerializedName("public")
    val public: Boolean
) : Parcelable

@Parcelize
data class DataModel(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: UserModel
) : Parcelable

@Parcelize
data class UserModel(
    val id: String,
    val status: String,
    val role: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val timezone: String,
    val locale: String,
    val locale_options: String,
    val avatar: String,
    val company: String,
    val title: String,
    val external_id: String,
    val password_reset_token: String,
    val parent: String,
    val birthday: String,
    val can_read: String,
    val can_count: String,
    val knows_alphabet: String
) : Parcelable
