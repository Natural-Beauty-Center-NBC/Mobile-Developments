package id.project.nbcmobile.view.components.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.data.source.remote.response.DetailProdukItem
import id.project.nbcmobile.data.source.remote.response.TransaksiItem
import id.project.nbcmobile.databinding.ItemJadwalDokterGuestBinding
import id.project.nbcmobile.view.components.utils.formatToRupiah

class DetailProdukAdapter : ListAdapter<DetailProdukItem, DetailProdukAdapter.DetailProdukViewHolder>(
    DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailProdukViewHolder {
        val binding = ItemJadwalDokterGuestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailProdukViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailProdukViewHolder, position: Int) {
        val detailProduk = getItem(position)
        holder.bind(detailProduk)
    }

    class DetailProdukViewHolder(private val binding: ItemJadwalDokterGuestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailProduk: DetailProdukItem) {
            binding.apply {
                itemName.text = detailProduk.produk.nama
                itemPhone.text = "Jumlah Pembelian : ${detailProduk.jumlahPembelian}"
                itemShift.text = "Total : ${detailProduk.subTotal.formatToRupiah()}"
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailProdukItem>() {
            override fun areItemsTheSame(oldItem: DetailProdukItem, newItem: DetailProdukItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DetailProdukItem,
                newItem: DetailProdukItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}