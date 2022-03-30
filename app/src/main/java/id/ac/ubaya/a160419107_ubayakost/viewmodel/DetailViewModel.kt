package id.ac.ubaya.a160419107_ubayakost.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.a160419107_ubayakost.model.KostUbaya

class DetailViewModel(application: Application): AndroidViewModel(application)  {
    val kostLD = MutableLiveData<KostUbaya>()

    val TAG = "volleyTag"
    private var queue: RequestQueue ?=null

    fun fetch(id: Int){
        queue =  Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/hybrid/160419107/anmp/listKost.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {   response ->
//
                val sType = object  : TypeToken<ArrayList<KostUbaya>>(){}.type
                val result = Gson().fromJson<ArrayList<KostUbaya>>(response, sType)
//                val result = Gson().fromJson<KostUbaya>(response, KostUbaya::class.java)

                for(data in result){
                    if(data.id == id){
                        kostLD.value= data
                    }
                }
                Log.d("showvolley", response.toString())
            },
            {
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)


    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}