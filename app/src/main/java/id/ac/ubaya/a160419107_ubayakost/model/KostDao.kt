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

//  Select data kost yang terfavorit
    @Query("SELECT * FROM kostubaya where isFavorite = 1")
    suspend fun selectFavoriteKost(): List<KostUbaya>

//    Select data kost untuk detail
    @Query("select * from kostubaya where id = :id")
    suspend fun selectSpesificKost(id:Int):KostUbaya

//    Update data kost
    @Query("update kostubaya set name= :nama, jenis= :jenis, " +
            "fasilitas= :fasilitas, alamat=:alamat, harga= :harga, photoUrl=:foto, rekening=:no_rek," +
            "atas_nama= :atasNama where id= :uuid")
    suspend fun updateKost(nama:String, jenis:String, fasilitas:String, alamat:String, harga:String,
                           foto:String, no_rek:String, atasNama:String, uuid:Int)

//    Update status favorit pada kost
    @Query("UPDATE kostubaya set isFavorite= :isfav WHERE id= :id")
    suspend fun updateIsFavorite(isfav:Int ,id:Int)

//    delete kost
    @Delete
    suspend fun deleteKost(kostUbaya: KostUbaya)

}