package id.project.nbcmobile.view.customer.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import id.project.nbcmobile.R
import id.project.nbcmobile.authentication.LoginActivity
import id.project.nbcmobile.databinding.ActivityMainBinding
import id.project.nbcmobile.view.components.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this@MainActivity) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()

            } else {
                navController = findNavController(R.id.nav_host_fragment_activity_main)
                binding.navView.setupWithNavController(navController)

                val navGraph = navController.navInflater.inflate(R.navigation.customer_navigation)
                val bundle = Bundle().apply {
                    putString("home-id", user.id)
                    putString("history-id", user.id)
                    putString("customer-profile-id", user.id)
                }

                navController.setGraph(navGraph, bundle)
                binding.navView.setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_home -> {
                            navController.navigate(R.id.navigation_home, bundle)
                            true
                        }

                        R.id.navigation_history -> {
                            navController.navigate(R.id.navigation_history, bundle)
                            true
                        }

                        R.id.navigation_profile_customer -> {
                            navController.navigate(R.id.navigation_profile_customer, bundle)
                            true
                        }

                        else -> false
                    }
                }
            }
        }
    }
}