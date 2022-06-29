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
import id.ac.ubaya.a160419107_ubayakost.util.buildDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BayarViewModel (application: Application): AndroidViewModel(application) , CoroutineScope {

    val bayarPesanLD = MutableLiveData<KostUbaya>()

    private var job = Job()
//    val TAG = "volleyTag"
//    private var queue: RequestQueue?=null
//
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetchbayar(id: Int){
        launch {
            val db = buildDatabase(getApplication())
            bayarPesanLD.value = db.kostDao().selectSpesificKost(id)
        }
//        queue =  Volley.newRequestQueue(getApplication())
//        val url = "https://ubaya.fun/hybrid/160419107/anmp/listKost.php"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {   response ->
////
//                val sType = object  : TypeToken<ArrayList<KostUbaya>>(){}.type
//                val result = Gson().fromJson<ArrayList<KostUbaya>>(response, sType)
////                val result = Gson().fromJson<KostUbaya>(response, KostUbaya::class.java)
//
//                for(data in result){
//                    if(data.id == id){
//                        bayarPesanLD.value= data
//                    }
//                }
//                Log.d("showvolley", response.toString())
//            },
//            {
//                Log.d("errorvolley", it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)


    }

}