package id.ac.ubaya.a160419107_ubayakost.model

import androidx.room.*

//Membuat method seperti insert, update, delete
@Dao
interface KostDao {
//    Insert Kost
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKost(vararg kostUbaya: KostUbaya)

//  Select semua data pada tabel kost ubaya
    @Query("Select * from kostubaya")
    suspend fun selectAllKost(): List<KostUbaya>

//    Select data kost untuk detail
    @Query("select * from kostubaya where uuid = :id")
    suspend fun selectSpesificKost(id:Int)

//    Update data kost
    @Query("update kostubaya set name= :nama, jenis= :jenis, " +
            "fasilitas= :fasilitas, alamat=:alamat, harga= :harga, photoUrl=:foto, rekening=:no_rek," +
            "atas_nama= :atasNama, isFavorite= :favorite")
    suspend fun updateKost(nama:String, jenis:String, fasilitas:String, alamat:String, harga:Double,
                           foto:String, no_rek:String, atasNama:String, favorite:Int)

//    delete kost
    @Delete
    suspend fun deleteKost(kostUbaya: KostUbaya)
}