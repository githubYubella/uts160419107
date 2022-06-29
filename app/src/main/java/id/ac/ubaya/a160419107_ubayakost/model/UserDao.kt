package id.ac.ubaya.a160419107_ubayakost.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserKost(vararg myuser:PenggunaKost)

    @Query("SELECT * FROM penggunakost where username = :username and password= :password")
    suspend fun selectUser(username:String, password:String): PenggunaKost

    @Query("SELECT * FROM penggunakost where username = :username")
    suspend fun selectSpesificUser(username:String): PenggunaKost
}