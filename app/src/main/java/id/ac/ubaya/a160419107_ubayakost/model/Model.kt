package id.ac.ubaya.a160419107_ubayakost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class KostUbaya(
    @ColumnInfo(name="name")
    var nama:String,
    @ColumnInfo(name="jenis")
    var jenis:String,
    @ColumnInfo(name="fasilitas")
    var fasilitas:String,
    @ColumnInfo(name="alamat")
    var alamat:String,
    @ColumnInfo(name="harga")
    var harga:String,
    @ColumnInfo(name="photoUrl")
    var photoUrl:String,
    @ColumnInfo(name="rekening")
    var rekening:String,
    @ColumnInfo(name="atas_nama")
    var atas_nama:String,
    @ColumnInfo(name="isFavorite")
    var isFavorite:Int = 0
    ){  @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}