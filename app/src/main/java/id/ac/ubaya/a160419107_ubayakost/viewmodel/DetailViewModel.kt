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

class DetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope  {
    val kostLD = MutableLiveData<KostUbaya>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

//    val TAG = "volleyTag"
//    private var queue: RequestQueue ?=null

    fun fetch(id: Int){
        launch {
            val db = buildDatabase(getApplication())
            kostLD.value = db.kostDao().selectSpesificKost(id)
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
//                        kostLD.value= data
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
//

    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }

    fun update(nama:String, jenis:String, fasilitas:String, alamat:String, harga:String,
               photoUrl:String, rekening:String, atas_nama:String, uuid:Int){
        launch {
            val db = buildDatabase(getApplication())
            db.kostDao().updateKost(nama, jenis, fasilitas, alamat, harga, photoUrl, rekening, atas_nama,uuid)
        }
    }

    fun updateFav(kost:KostUbaya){
        launch {
            val db = buildDatabase(getApplication())
            if(kost.isFavorite == 0){
                db.kostDao().updateIsFavorite(1,kost.id)
            }
            else{
                db.kostDao().updateIsFavorite(0,kost.id)
            }
        }

    }
}