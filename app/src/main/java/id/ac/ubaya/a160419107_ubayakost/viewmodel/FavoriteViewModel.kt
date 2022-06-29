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

class FavoriteViewModel (application: Application) : AndroidViewModel(application), CoroutineScope  {
    val kostLiveData= MutableLiveData<List<KostUbaya>>()
    val kostLoadErrorLiveData= MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
//    val TAG = "volleyTag"
//    private var queue: RequestQueue? =null

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh(){
        kostLoadErrorLiveData.value=false
        loadingLiveData.value=true

        launch {
            val db = buildDatabase(getApplication())

            kostLiveData.value = db.kostDao().selectFavoriteKost()
            loadingLiveData.value=false
        }

//
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://ubaya.fun/hybrid/160419107/anmp/favorite.php"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response->
//                val sType = object  : TypeToken<ArrayList<KostUbaya>>(){}.type
//                val result = Gson().fromJson<ArrayList<KostUbaya>>(response, sType)
//                kostLiveData.value= result
//                loadingLiveData.value=false
//                Log.d("showvolley",response.toString())
//            },
//            {
//                loadingLiveData.value=false
//                kostLoadErrorLiveData.value=true
//                Log.d("error volley",it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)
//
//    }
//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)

    }

}