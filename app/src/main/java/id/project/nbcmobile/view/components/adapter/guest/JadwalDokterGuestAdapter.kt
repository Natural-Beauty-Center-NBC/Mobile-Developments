package id.project.nbcmobile.view.components.adapter.guest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.data.source.remote.response.JadwalItem
import id.project.nbcmobile.databinding.ItemJadwalDokterGuestBinding

class JadwalDokterGuestAdapter :
    ListAdapter<JadwalItem, JadwalDokterGuestAdapter.JadwalDokterGuestViewHolder>(
        DIFF_CALLBACK
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalDokterGuestViewHolder {
        val binding = ItemJadwalDokterGuestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return JadwalDokterGuestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JadwalDokterGuestViewHolder, position: Int) {
        val jadwal = getItem(position)
        holder.bind(jadwal)
    }

    class JadwalDokterGuestViewHolder(private val binding: ItemJadwalDokterGuestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(jadwal: JadwalItem) {
            binding.apply {
                itemName.text = jadwal.dokter.nama
                itemPhone.text = jadwal.dokter.noTelp
                itemShift.text = "${jadwal.shift.startAt.substring(0, 5)} - ${jadwal.shift.endAt.substring(0, 5)}"
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<JadwalItem>() {
            override fun areItemsTheSame(
                oldItem: JadwalItem,
                newItem: JadwalItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: JadwalItem,
                newItem: JadwalItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}