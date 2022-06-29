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
import id.ac.ubaya.a160419107_ubayakost.model.PenggunaKost

import id.ac.ubaya.a160419107_ubayakost.model.ProfilUser
import id.ac.ubaya.a160419107_ubayakost.util.buildDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfilViewModel (application: Application): AndroidViewModel(application), CoroutineScope {

    val profilLD = MutableLiveData<ProfilUser>()
    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    val penggunaLD = MutableLiveData<PenggunaKost>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    fun fetchProfil(email: String){
        queue =  Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/hybrid/160419107/anmp/data_user.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {   response ->
//
                val sType = object  : TypeToken<ProfilUser>(){}.type
                val result = Gson().fromJson<ProfilUser>(response, sType)

//                for(data in result){
                    if(result.email == email){
                        profilLD.value= result
                    }
//                }
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


    fun addUser(list: List<PenggunaKost>){
        launch{

            val db = buildDatabase(getApplication())

            db.userDao().insertUserKost(*list.toTypedArray())

        }

    }

    fun validation(user:String, pass:String){

        launch {
            val db = buildDatabase(getApplication())

            penggunaLD.value =
                db.userDao().selectUser(user, pass)
        }

    }

    fun peran(user:String){

        launch {
            val db = buildDatabase(getApplication())

            penggunaLD.value = db.userDao().selectSpesificUser(user)

        }

    }
}