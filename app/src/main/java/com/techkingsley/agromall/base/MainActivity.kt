package com.techkingsley.agromall.base

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.techkingsley.agromall.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = this.findNavController(R.id.nav_host)
        setSupportActionBar(toolbar)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                destination.id.toString()
            }
            hideToolbar(destination.id)
            Log.d("NavigationActivity", "Navigated to $dest")
        }

        setupActionBar(navController)
    }

    private fun setupActionBar(navController: NavController) {
        // This allows NavigationUI to decide what label to show in the action bar
        // By using appBarConfig, it will also determine whether to
        // show the up arrow or drawer menu icon
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.registrationFragment, R.id.dashboardFragment))
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun hideToolbar(destination: Int) {
        when (destination) {
            R.id.loginFragment -> toolbar.visibility = View.GONE
            R.id.onBoardingFragment -> toolbar.visibility = View.GONE
            R.id.registrationFragment -> toolbar.visibility = View.VISIBLE
            R.id.dashboardFragment -> toolbar.visibility = View.VISIBLE
            else -> toolbar.visibility = View.VISIBLE
        }
    }
}
