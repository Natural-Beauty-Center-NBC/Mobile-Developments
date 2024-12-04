package id.project.nbcmobile.view.components.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.data.source.remote.response.ListRuanganItem
import id.project.nbcmobile.databinding.ItemRuanganBinding

class AvailableRuanganAdapter :
    ListAdapter<ListRuanganItem, AvailableRuanganAdapter.AvailableRuanganViewHolder>(
        DIFF_CALLBACK
    ) {
    private lateinit var onClickCallback: OnClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableRuanganViewHolder {
        val binding = ItemRuanganBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AvailableRuanganViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AvailableRuanganViewHolder, position: Int) {
        val ruangan = getItem(position)
        holder.bind(ruangan, onClickCallback)
    }

    class AvailableRuanganViewHolder(private val binding: ItemRuanganBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ruangan: ListRuanganItem, onClickCallback: OnClickCallback) {
            binding.apply {
                noRuang.text = "Ruangan ${ruangan.noRuangan}"
                status.text = ruangan.status
                switchAssign.isChecked = false

                switchAssign.setOnClickListener {
                    onClickCallback.onSwitchClicked(ruangan.id)
                }
            }
        }
    }

    fun setOnItemClickCallback(onClickCallback: OnClickCallback) {
        this.onClickCallback = onClickCallback
    }

    interface OnClickCallback {
        fun onSwitchClicked(idRuangan: Int)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListRuanganItem>() {
            override fun areItemsTheSame(
                oldItem: ListRuanganItem,
                newItem: ListRuanganItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListRuanganItem,
                newItem: ListRuanganItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}