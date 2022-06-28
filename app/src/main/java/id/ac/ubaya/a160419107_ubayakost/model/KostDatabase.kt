package id.ac.ubaya.a160419107_ubayakost.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Kalau ditambahkan migration jangan lupa versinya dinaikkan
//contoh versinya menjadi 2 dan untuk menambahkan tabel lagi seperti berikut
//@Database(entities = [KostUbaya::class, PenggunaKost::class], version =  2)
@Database(entities = arrayOf(KostUbaya::class), version = 2)
abstract class KostDatabase:RoomDatabase() {
    abstract fun kostDao(): KostDao

    companion object{
        @Volatile
        private var instance: KostDatabase ?= null
        private val Lock = Any()

//        buat database
        private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext, KostDatabase::class.java, "kostdb"
        ).build()

//  Kalau mau ditambahkan migration
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                TodoDatabase::class.java,
//                "kostdb"
//            ).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4).build()


        operator fun invoke(context: Context){
            if(instance != null){
                synchronized(Lock){
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }

            }
        }


    }

}