package id.project.nbcmobile.view.components.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.data.source.remote.response.TransaksiItem
import id.project.nbcmobile.databinding.ItemTransactionHistoryBinding
import id.project.nbcmobile.view.components.utils.formatDate
import id.project.nbcmobile.view.components.utils.formatToRupiah
import id.project.nbcmobile.view.customer.history.DetailTransactionActivity

class TransactionHistoryAdapter :
    ListAdapter<TransaksiItem, TransactionHistoryAdapter.TransactionHistoryViewHolder>(
        DIFF_CALLBACK
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionHistoryViewHolder {
        val binding = ItemTransactionHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TransactionHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionHistoryViewHolder, position: Int) {
        val transactionHistory = getItem(position)
        holder.bind(transactionHistory)
    }

    class TransactionHistoryViewHolder(private val binding: ItemTransactionHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: TransaksiItem) {
            binding.apply {
                transactionNum.text = transaction.noTransaksi
                transactionDate.text = transaction.tanggalTransaksi.formatDate()
                transactionType.text = transaction.jenisTransaksi
                transactionPoint.text = "Point Earned: ${transaction.poinEarned}"
                transactionDiscount.text = "Discount: ${transaction.diskon}"
                transactionTotal.text = "Total Pembelian: ${transaction.totalHarga.formatToRupiah()}"
            }

            itemView.setOnClickListener {
                val intentToDetail = Intent(itemView.context, DetailTransactionActivity::class.java)
                intentToDetail.putExtra(DetailTransactionActivity.NO_TRANSACTION, transaction.noTransaksi)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TransaksiItem>() {
            override fun areItemsTheSame(oldItem: TransaksiItem, newItem: TransaksiItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: TransaksiItem,
                newItem: TransaksiItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}