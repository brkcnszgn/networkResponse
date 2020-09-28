package com.brkcnszgn.testcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import okhttp3.internal.wait


class MainActivity : AppCompatActivity() {

    private var userViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)

        userViewModel!!.executeRequest().also {
            userViewModel!!.resultSucces.observe(this,{
                if (!responseError(HttpStatus.code,null)){
                    if (it != null)
                        Log.e("TAG","gelen data $it")
                }

            })

            userViewModel!!.responseErrorModel.observe(this,{
                if (responseError(HttpStatus.code,it)){
                    if (it != null)
                        Log.e("TAG","gelen data $it")
                }

            })

            userViewModel!!.loading.observe(this,{
                if (!it){
                    Toast.makeText(this,"Progress kapalı",Toast.LENGTH_SHORT).show()
                    return@observe
                }
                Toast.makeText(this,"Progress Açık",Toast.LENGTH_SHORT).show()

            })
        }


    }

   private fun responseError(code : Int?,errorBody: ErrorBody?): Boolean {
        return when (code) {
            200 -> {
                Log.e("TAG","Log yakalndı ${code}")
                false
            }
            400 -> {
                // showAlertWarning("Girmiş olduğunuz kullanıcı adı yada şifre hatalıdır!")
                true
            }
            422 -> {
                if (errorBody?.error?.code == 114){

                    Log.e("TAG","Böyle bir kullanıcı bbulunmamaktadır ${code}")
                    return true
                }
                Log.e("TAG","Log yakalndı ${code}")
                // showAlertWarning("Hatalı giriş yapıldı! \n Lütfen Kullanıcı Adı veya Şifrenizi kontrol ediniz")
                true
            }
            422 -> {
                if (errorBody?.code == 114){

                       Log.e("TAG","Böyle bir kullanıcı bbulunmamaktadır ${code}")
                  return true
                }
                // showAlertWarning("404 - KidsVid Servislerine ulaşılamıyor. Lütfen daha sonra tekrar deneyiniz!")
                true
            }
            407 -> {
                //  showAlertWarning("407 - Proxy Authorization Required!")
                true
            }
            405 -> {
                // showAlertWarning("404 - KidsVid Servislerine ulaşılamıyor. Lütfen daha sonra tekrar deneyiniz!")
                true
            }
            500 -> {
                //  showAlertWarning("500 - KidsVid Servislerine ulaşılamıyor. Lütfen daha sonra tekrar deneyiniz!")
                true
            }
            502 -> {
                //  showAlertWarning("502 - KidsVid Servislerine ulaşılamıyor. Lütfen daha sonra tekrar deneyiniz!")
                true
            }
            503 -> {
                //  showAlertWarning("503 - KidsVid Servislerine ulaşılamıyor. Lütfen daha sonra tekrar deneyiniz!")
                true
            }
            504 -> {
                //  showAlertWarning("504 - KidsVid Servislerine ulaşılamıyor. Lütfen internet bağlantınızın Turkcell güvenli ağına erişebildiğini kontrol edip daha sonra tekrar deneyiniz.")
                true
            }
            else -> {
                //   showAlertWarning("Veri bulunamadı")
                true
            }
        }
    }
}