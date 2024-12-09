package id.project.nbcmobile.view.guest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.R
import id.project.nbcmobile.authentication.LoginActivity
import id.project.nbcmobile.data.source.remote.response.DataItem
import id.project.nbcmobile.data.source.remote.response.PerawatansItem
import id.project.nbcmobile.data.source.remote.response.ProduksItem
import id.project.nbcmobile.databinding.ActivityHomeGuestBinding
import id.project.nbcmobile.view.components.adapter.guest.JadwalDokterGuestAdapter
import id.project.nbcmobile.view.components.adapter.guest.ListPerawatanGuestAdapter
import id.project.nbcmobile.view.components.adapter.guest.ListProdukGuestAdapter
import id.project.nbcmobile.view.components.utils.ViewModelFactory

class HomeGuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeGuestBinding
    private val viewModel by viewModels<HomeGuestViewModel> {
        ViewModelFactory.getInstance(this@HomeGuestActivity)
    }

    private lateinit var rvProduk: RecyclerView
    private lateinit var rvPerawatan: RecyclerView
    private lateinit var rvDokter: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getListProduk()
        viewModel.getListPerawatan()
        viewModel.getJadwalDokter()

        binding.apply {
            rvProduk = rvProdukGuest
            rvProduk.layoutManager =
                LinearLayoutManager(this@HomeGuestActivity, LinearLayoutManager.HORIZONTAL, false)

            rvPerawatan = rvPerawatanGuest
            rvPerawatan.layoutManager =
                LinearLayoutManager(this@HomeGuestActivity, LinearLayoutManager.HORIZONTAL, false)

            rvDokter = rvDokterGuest
            rvDokter.layoutManager = LinearLayoutManager(this@HomeGuestActivity)
        }

        setupAction()
        setupView()
    }

    private fun setupView() {
        viewModel.isLoading.observe(this@HomeGuestActivity) {
            showLoading(it)
        }

        viewModel.getListProduk.observe(this@HomeGuestActivity) { response ->
            setupListProduk(response.produks.take(5))
        }

        viewModel.getListPerawatan.observe(this@HomeGuestActivity) { response ->
            setupListPerawatan(response.perawatans.take(3))
        }

        viewModel.getJadwalDokter.observe(this@HomeGuestActivity) { response ->
            setupJadwalDokter(response.data[0])
        }
    }

    private fun setupAction() {
        binding.apply {
            loginGuestButton.setOnClickListener {
                startActivity(Intent(this@HomeGuestActivity, LoginActivity::class.java))
                finish()
            }

            seeAllProduk.setOnClickListener {
                Toast.makeText(
                    this@HomeGuestActivity,
                    "Coming Soon! Stay Tuned!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            seeAllPerawatan.setOnClickListener {
                Toast.makeText(
                    this@HomeGuestActivity,
                    "Coming Soon! Stay Tuned!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            seeAllDokter.setOnClickListener {
                Toast.makeText(
                    this@HomeGuestActivity,
                    "Coming Soon! Stay Tuned!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupListProduk(produks: List<ProduksItem>) {
        val adapter = ListProdukGuestAdapter()
        adapter.submitList(produks)
        rvProduk.adapter = adapter
    }

    private fun setupListPerawatan(perawatans: List<PerawatansItem>) {
        val adapter = ListPerawatanGuestAdapter()
        adapter.submitList(perawatans)
        rvPerawatan.adapter = adapter
    }

    private fun setupJadwalDokter(data: DataItem) {
        binding.titleDokter.text = getString(R.string.jadwal_dokter, data.hari)
        val adapter = JadwalDokterGuestAdapter()
        adapter.submitList(data.jadwal)
        rvDokter.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}