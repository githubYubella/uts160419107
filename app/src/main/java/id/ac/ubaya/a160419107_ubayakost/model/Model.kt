package id.ac.ubaya.a160419107_ubayakost.model

import com.google.gson.annotations.SerializedName

data class KostUbaya(
    @SerializedName("id")
    var id:Int?,
    @SerializedName("name")
    var nama:String?,
    @SerializedName("jenis")
    var jenis:String?,
    @SerializedName("fasilitas")
    var fasilitas:String?,
    var alamat:String?,
    @SerializedName("harga")
    var harga:String?,
    @SerializedName("photoUrl")
    var photoUrl:String?,
    var rekening:String?,
    var atas_nama:String
)