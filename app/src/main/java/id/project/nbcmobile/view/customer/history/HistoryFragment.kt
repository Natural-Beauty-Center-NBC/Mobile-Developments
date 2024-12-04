package id.project.nbcmobile.view.customer.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.data.source.remote.response.TransaksiItem
import id.project.nbcmobile.databinding.FragmentHistoryBinding
import id.project.nbcmobile.view.components.adapter.TransactionHistoryAdapter
import id.project.nbcmobile.view.components.utils.ViewModelFactory

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvTransactionHistory: RecyclerView
    private val viewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idCustomer = arguments?.getString("history-id").toString()
        viewModel.getTransactionHistoryData(id = idCustomer)

        rvTransactionHistory = binding.rvTransactionHistory
        rvTransactionHistory.layoutManager = LinearLayoutManager(requireActivity())

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.getUserTransaction.observe(viewLifecycleOwner) { response ->
            if (response.transaksi.isEmpty()) {
                binding.errorRvHistory.visibility = View.VISIBLE
            } else {
                binding.errorRvHistory.visibility = View.GONE
                setupTransactionHistory(response.transaksi)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupTransactionHistory(transactions: List<TransaksiItem>) {
        val adapter = TransactionHistoryAdapter()
        adapter.submitList(transactions)
        rvTransactionHistory.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}