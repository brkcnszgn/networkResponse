package com.brkcnszgn.testcoroutine

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.haroldadmin.cnradapter.invoke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel() : ViewModel() {
    private val service: Service
    val resultSucces2 =  MutableLiveData<LoginModel>()
    val resultSucces :  LiveData<LoginModel> = resultSucces2

    val resultErrorModel = MutableLiveData<ErrorBody>()
    val responseErrorModel: LiveData<ErrorBody> = resultErrorModel
    var loading = MutableLiveData<Boolean>()

    init {
        val mockInterceptor = KidsVidRequestInterceptor()
        val mockClient = OkHttpClient.Builder()
            .addInterceptor(mockInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://kidsapi.mtek.me/")
            .client(mockClient)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(mockClient)
            .build()

        service = retrofit.create(Service::class.java)
    }

    fun executeRequest() {
        val model = RequestTokenModel()
        model.email = "fatih.piskin"
        model.password = "fatih1234"




        loading.value = true
        Log.d(TAG,"Loading açıldı")
//    val model = RequestTokenModel()
//        model.email = "fatih.piskin@mtekbilisim.com"
//        model.password = "fatih1234"
        viewModelScope.launch(Dispatchers.Main) {
            val bar = service.getPerson(model)
            when (bar) {
                is NetworkResponse.Success<LoginModel> -> {
                    Log.d(TAG, "Success ${bar.body}")
                    Log.d(TAG,"Loading kapandı")
                    loading.value = false
                    resultSucces2.value = bar.body

                }
                is NetworkResponse.ServerError<ErrorBody> -> {
                    Log.d(TAG,"Loading kapandı")
                    loading.value = false
                    Log.d(TAG, "ApiError ${bar.body}")
                    resultErrorModel.postValue(bar.body)
                    HttpStatus.code = bar.code

                }
                is NetworkResponse.NetworkError -> {
                    Log.d(TAG, "NetworkError ${bar.error.hashCode()}")
                    HttpStatus.code = bar.error.hashCode()
                }
                is NetworkResponse.UnknownError -> Log.d(TAG, "UnknownError")
            }

          //  val bars = service.getBars()
           // Log.i("test", bars.toString())
        }
    }
}