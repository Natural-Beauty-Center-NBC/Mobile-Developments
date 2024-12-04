package id.project.nbcmobile.view.customer.history

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.data.source.remote.response.DetailPerawatanItem
import id.project.nbcmobile.data.source.remote.response.DetailProdukItem
import id.project.nbcmobile.data.source.remote.response.Transaksi
import id.project.nbcmobile.databinding.ActivityDetailTransactionBinding
import id.project.nbcmobile.view.components.adapter.DetailPerawatanAdapter
import id.project.nbcmobile.view.components.adapter.DetailProdukAdapter
import id.project.nbcmobile.view.components.utils.ViewModelFactory
import id.project.nbcmobile.view.components.utils.formatToRupiah

class DetailTransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTransactionBinding
    private val viewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(this@DetailTransactionActivity)
    }
    private lateinit var rvDetailProduks: RecyclerView
    private lateinit var rvDetailPerawatans: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noTransaction = intent.getStringExtra(NO_TRANSACTION).toString()
        viewModel.getDetailTransaction(id = noTransaction)

        binding.apply {
            rvDetailProduks = rvDetailProduk
            rvDetailProduks.layoutManager = LinearLayoutManager(this@DetailTransactionActivity)

            rvDetailPerawatans = rvDetailPerawatan
            rvDetailPerawatans.layoutManager = LinearLayoutManager(this@DetailTransactionActivity)

            backButton.setOnClickListener {
                finish()
            }
        }

        viewModel.getDetailTransaction.observe(this@DetailTransactionActivity) { response ->
            setupDetailTransaksi(response.data.transaksi)
            setupDetailProduk(response.data.detailProduk)
            setupDetailPerawatan(response.data.detailPerawatan)
        }
    }

    private fun setupDetailTransaksi(transaksi: Transaksi) {
        binding.apply {
            noTransaksi.text = "Nomor Transaksi : ${transaksi.noTransaksi}"
            tanggalTransaksi.text = "tanggal Transaksi : ${transaksi.tanggalTransaksi}"
            jenisTransaksi.text = "Jenis Transaksi : ${transaksi.jenisTransaksi}"
            statusPembayaran.text = "Status Pembayaran : ${transaksi.statusPembayaran}"
            totalTransaksi.text = "Total Pembayaran : ${transaksi.totalHarga.formatToRupiah()}"
        }
    }

    private fun setupDetailProduk(detailProduk: List<DetailProdukItem>) {
        val adapter = DetailProdukAdapter()
        adapter.submitList(detailProduk)
        rvDetailProduks.adapter = adapter
    }

    private fun setupDetailPerawatan(detailPerawatan: List<DetailPerawatanItem>) {
        val adapter = DetailPerawatanAdapter()
        adapter.submitList(detailPerawatan)
        rvDetailPerawatans.adapter = adapter
    }

    companion object {
        const val NO_TRANSACTION = "no_transaction"
    }
}