package id.ac.ubaya.a160419107_ubayakost.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.model.KostDatabase
import java.lang.Exception

val db_name="kostdb"

fun ImageView.loadImage(url:String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback   {
            override fun onSuccess() {
                progressBar.visibility= View.GONE
//                gone
            }

            override fun onError(e: Exception?) {

            }

        })
}

fun buildDatabase(context: Context):KostDatabase{
   val db = Room.databaseBuilder(
       context, KostDatabase::class.java, db_name
   ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()

    return db
}

val MIGRATION_1_2 =object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE penggunakost (id INTEGER PRIMARY KEY NOT NULL, nama TEXT, email TEXT," +
                    " telepon TEXT, username TEXT, password TEXT, peran TEXT)"
        )

    }
}

val MIGRATION_2_3 =object : Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE transaksikost (id INTEGER PRIMARY KEY NOT NULL,tanggal INTEGER, idKost INTEGER, username TEXT)"
        )

    }
}
//builDatabase ini juga perlu ditambahkan migration

//fun buildDb(context: Context):UbayaKostDatabase{
//    val db = Room.databaseBuilder(context, UbayaKostDatabase::class.java, DB_NAME).addMigrations(MIGRATION_1_2).build()
//    return db
//}

//contoh ingin menambahkan migration berupa pembuatan tabel pengguna
//val MIGRATION_1_2 =object : Migration(1,2){
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "CREATE TABLE penggunakost (id INTEGER PRIMARY KEY NOT NULL, nama TEXT, email TEXT," +
//                    " telepon TEXT, username TEXT, password TEXT, peran TEXT)"
//        )
//
//    }
//}
//Tambah kolom
//val MIGRATION_2_3 =object : Migration(2,3){
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 not null"
//        )
//
//    }
//}

@BindingAdapter("android:imageUrl", "android:progreesBar")
fun loadPhotoUrl(view: ImageView,url:String?, progressBar:ProgressBar){
    view.loadImage(url,progressBar)
}