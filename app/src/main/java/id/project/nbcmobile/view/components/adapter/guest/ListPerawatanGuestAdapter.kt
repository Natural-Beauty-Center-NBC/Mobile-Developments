package id.project.nbcmobile.view.components.adapter.guest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.data.source.remote.response.PerawatansItem
import id.project.nbcmobile.databinding.ItemListPerawatanGuestBinding

class ListPerawatanGuestAdapter :
    ListAdapter<PerawatansItem, ListPerawatanGuestAdapter.ListPerawatanGuestViewHolder>(
        DIFF_CALLBACK
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPerawatanGuestViewHolder {
        val binding = ItemListPerawatanGuestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListPerawatanGuestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListPerawatanGuestViewHolder, position: Int) {
        val perawatan = getItem(position)
        holder.bind(perawatan)
    }

    class ListPerawatanGuestViewHolder(private val binding: ItemListPerawatanGuestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(perawatan: PerawatansItem) {
            binding.apply {
                itemName.text = perawatan.nama
                itemType.text = perawatan.tipe
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PerawatansItem>() {
            override fun areItemsTheSame(
                oldItem: PerawatansItem,
                newItem: PerawatansItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PerawatansItem,
                newItem: PerawatansItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}