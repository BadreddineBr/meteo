package com.example.meteoApp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.meteoApp.data.WeatherRepository
import com.example.meteoApp.feature.home.HomeScreenFragmentDirections
import com.example.meteoApp.feature.permission.PermissionScreenFragmentDirections
import com.example.meteoApp.util.Location.requiredPermission
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: WeatherRepository

    private lateinit var navController: NavController
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach {
            if (it.key in requiredPermission && !it.value) {
                return@registerForActivityResult
            }
        }
        navController.navigate(
            PermissionScreenFragmentDirections.actionPermissionScreenFragmentToHomeScreenFragment()
        )
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            refreshContent()
        }

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize NavController
        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

        // Check and request permissions
        if (!requiredPermission.all { ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED }) {
            navController.navigate(
                HomeScreenFragmentDirections.actionHomeScreenFragmentToPermissionScreenFragment()
            )
            permissionLauncher.launch(requiredPermission)
        }
    }

    private fun refreshContent() {
        // Add logic to refresh data here
        // Example: Simulating a network or data fetch delay
        Handler(Looper.getMainLooper()).postDelayed({
            // Stop refresh animation
            swipeRefreshLayout.isRefreshing = false
        }, 2000) // Adjust delay as necessary
    }
}
