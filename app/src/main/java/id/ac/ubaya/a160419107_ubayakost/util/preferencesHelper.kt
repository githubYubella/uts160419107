package id.ac.ubaya.a160419107_ubayakost.util

import android.content.Context
import android.content.SharedPreferences

class preferencesHelper(context: Context) {
    val pref_name = "login"
    val key = "isLogin"
    val keyUsername = "myUser"
    val sharePref:SharedPreferences
    val editor:SharedPreferences.Editor

    init {
        sharePref = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE)
        editor = sharePref.edit()
    }
//mengetahui apakah sudah login atau belum
    fun putLogin(value:Boolean){
        editor.putBoolean(key, value).apply()
    }
//menyimpan data user yang login
    fun putUsername(value:String){
        editor.putString(keyUsername, value).apply()
    }

    fun getBoolean():Boolean{
        return sharePref.getBoolean(key, false)
    }

    fun getUsername():String{
        return sharePref.getString(keyUsername, "").toString()
    }

    fun clear(){
        editor.clear().apply()
    }
}