package id.project.nbcmobile.view.components.adapter.guest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.data.source.remote.response.ProduksItem
import id.project.nbcmobile.databinding.ItemListProdukGuestBinding

class ListProdukGuestAdapter :
    ListAdapter<ProduksItem, ListProdukGuestAdapter.ListProdukGuestViewHolder>(
        DIFF_CALLBACK
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListProdukGuestViewHolder {
        val binding = ItemListProdukGuestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListProdukGuestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListProdukGuestViewHolder, position: Int) {
        val produk = getItem(position)
        holder.bind(produk)
    }

    class ListProdukGuestViewHolder(private val binding: ItemListProdukGuestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(produk: ProduksItem) {
            binding.apply {
                itemName.text = produk.nama
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProduksItem>() {
            override fun areItemsTheSame(oldItem: ProduksItem, newItem: ProduksItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ProduksItem,
                newItem: ProduksItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}