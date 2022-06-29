package id.ac.ubaya.a160419107_ubayakost.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface TransaksiDao {
    // insert data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTanggal(vararg transaksi: TransaksiKost)
}