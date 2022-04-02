package id.ac.ubaya.a160419107_ubayakost.model

import com.google.gson.annotations.SerializedName

data class KostUbaya(
    @SerializedName("id")
    val id:Int?,
    @SerializedName("name")
    val nama:String?,
    @SerializedName("jenis")
    val jenis:String?,
    @SerializedName("fasilitas")
    val fasilitas:String?,
    val alamat:String?,
    @SerializedName("harga")
    val harga:String?,
    @SerializedName("photoUrl")
    val photoUrl:String?,
    val rekening:String?,
    val atas_nama:String
)