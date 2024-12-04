package id.project.nbcmobile.view.components.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.data.source.remote.response.DetailPerawatanItem
import id.project.nbcmobile.databinding.ItemJadwalDokterGuestBinding
import id.project.nbcmobile.view.components.utils.formatToRupiah

class DetailPerawatanAdapter :
    ListAdapter<DetailPerawatanItem, DetailPerawatanAdapter.DetailPerawatanViewHolder>(
        DIFF_CALLBACK
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPerawatanViewHolder {
        val binding = ItemJadwalDokterGuestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailPerawatanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailPerawatanViewHolder, position: Int) {
        val detailPerawatan = getItem(position)
        holder.bind(detailPerawatan)
    }

    class DetailPerawatanViewHolder(private val binding: ItemJadwalDokterGuestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailPerawatan: DetailPerawatanItem) {
            binding.apply {
                itemName.text = detailPerawatan.perawatan.nama
                itemPhone.text = "Jumlah Pembelian : ${detailPerawatan.jumlahPembelian}"
                itemShift.text = "Total : ${detailPerawatan.subTotal.formatToRupiah()}"
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailPerawatanItem>() {
            override fun areItemsTheSame(
                oldItem: DetailPerawatanItem,
                newItem: DetailPerawatanItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DetailPerawatanItem,
                newItem: DetailPerawatanItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}