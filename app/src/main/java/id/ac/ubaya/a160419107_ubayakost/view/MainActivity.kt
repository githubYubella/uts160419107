package id.ac.ubaya.a160419107_ubayakost.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.util.preferencesHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var sph: preferencesHelper

    private var backPress = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sph = preferencesHelper(this)

        navController=(supportFragmentManager.findFragmentById(R.id.hostFragment)as 
                NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)
        bottomNav.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayout) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if(backPress + 2000 > System.currentTimeMillis()) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Keluar")
            builder.setMessage("Yakin keluar dari aplikasi ?")

            builder.setNegativeButton("Tidak") { Dialog, which ->

            }

            builder.setPositiveButton("Ya") { Dialog, which ->
                sph.clear()
                finishAffinity()

            }

            val buildDialog = builder.create()
            buildDialog.show()
        }
        else{
            Toast.makeText(this, "Tekan 2x untuk keluar", Toast.LENGTH_SHORT).show()
        }
        backPress = System.currentTimeMillis()
    }


}