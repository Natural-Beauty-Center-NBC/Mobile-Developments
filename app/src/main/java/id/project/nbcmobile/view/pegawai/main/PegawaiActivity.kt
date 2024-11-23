package id.project.nbcmobile.view.pegawai.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import id.project.nbcmobile.R
import id.project.nbcmobile.authentication.LoginActivity
import id.project.nbcmobile.databinding.ActivityPegawaiBinding
import id.project.nbcmobile.view.components.utils.ViewModelFactory

class PegawaiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPegawaiBinding
    private lateinit var navController: NavController
    private val viewModel by viewModels<PegawaiViewModel> {
        ViewModelFactory.getInstance(this@PegawaiActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this@PegawaiActivity) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this@PegawaiActivity, LoginActivity::class.java))
                finish()

            } else {
                navController = findNavController(R.id.nav_host_fragment_activity_pegawai)
                binding.navView.setupWithNavController(navController)

                val navGraph = navController.navInflater.inflate(R.navigation.pegawai_navigation)
                val bundle = Bundle().apply {
                    putInt("room-manage-id", user.id.toInt())
                    putInt("pegawai-profile-id", user.id.toInt())
                }

                navController.setGraph(navGraph, bundle)
                binding.navView.setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_room_management -> {
                            navController.navigate(R.id.navigation_room_management, bundle)
                            true
                        }

                        R.id.navigation_profile_pegawai -> {
                            navController.navigate(R.id.navigation_profile_pegawai, bundle)
                            true
                        }

                        else -> false
                    }
                }
            }
        }
    }
}