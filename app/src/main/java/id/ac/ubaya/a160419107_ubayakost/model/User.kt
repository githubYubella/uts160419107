package id.ac.ubaya.a160419107_ubayakost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PenggunaKost(
    @ColumnInfo(name="nama")
    var nama:String?,
    @ColumnInfo(name="email")
    var email:String?,
    @ColumnInfo(name="telepon")
    var telepon:String?,
    @ColumnInfo(name="username")
    var username:String?,
    @ColumnInfo(name="password")
    var password:String?,
    @ColumnInfo(name="peran")
    var peran:String?,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}