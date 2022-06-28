package id.ac.ubaya.a160419107_ubayakost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KategoriKost(
    @ColumnInfo(name="nama")
    var nama:String,
    ){@PrimaryKey(autoGenerate = true)
    var uuid:Int = 0

}
