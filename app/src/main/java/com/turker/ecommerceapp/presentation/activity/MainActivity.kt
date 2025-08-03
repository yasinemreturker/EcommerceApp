package com.turker.ecommerceapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.turker.ecommerceapp.R
import com.turker.ecommerceapp.databinding.ActivityMainBinding
import com.turker.ecommerceapp.util.invisible
import com.turker.ecommerceapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initBottomNavigationView()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initBottomNavigationView() {
        with(binding) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)

            navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.homeFragment || destination.id == R.id.cartFragment
                    || destination.id == R.id.favoriteFragment || destination.id == R.id.profileFragment
                ) {
                    bottomNavigationView.visible()
                } else {
                    bottomNavigationView.invisible()
                }
            }

        }
    }
}

