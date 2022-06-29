package id.ac.ubaya.a160419107_ubayakost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransaksiKost(
    @ColumnInfo(name="tanggal")
    var tanggal:Int,
    @ColumnInfo(name="idKost")
    var idKost:Int,
    @ColumnInfo(name="username")
    var username:String,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
