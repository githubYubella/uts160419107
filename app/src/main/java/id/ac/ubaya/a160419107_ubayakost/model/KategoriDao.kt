package id.ac.ubaya.a160419107_ubayakost.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface KategoriDao {
    @Query("Select * from kategorikost")
    suspend fun selectAllKategori(): List<KategoriKost>
}