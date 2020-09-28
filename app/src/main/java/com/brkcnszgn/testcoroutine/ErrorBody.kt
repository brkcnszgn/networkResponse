package com.brkcnszgn.testcoroutine

import com.google.gson.annotations.SerializedName
import java.lang.Error

class ErrorBody(){
    var code:Int? = null
     val error : Errors? = null
    override fun toString(): String {
        return "ErrorBody(error=$error)"
    }


}


 class Errors(){
     var code:Int? = null
     var message:String? =null
     @SerializedName("class")
     var classes : String? =null
     var file : String?=null
     var line : Int? =null

     override fun toString(): String {
         return "Errors(code=$code, message=$message, classes=$classes, file=$file, line=$line)"
     }


 }

