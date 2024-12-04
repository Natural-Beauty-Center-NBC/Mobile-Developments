package id.project.nbcmobile.view.customer.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.R
import id.project.nbcmobile.data.source.remote.response.Pegawai
import id.project.nbcmobile.data.source.remote.response.TransaksiItem
import id.project.nbcmobile.data.source.remote.response.User
import id.project.nbcmobile.databinding.FragmentProfileCustomerBinding
import id.project.nbcmobile.view.components.adapter.TransactionHistoryAdapter
import id.project.nbcmobile.view.components.customView.AlertDialogCustom
import id.project.nbcmobile.view.components.utils.ViewModelFactory

class CustomerProfileFragment : Fragment() {
    private var _binding: FragmentProfileCustomerBinding? = null
    private val binding get() = _binding!!

    private lateinit var alertDialog: AlertDialogCustom
    private val viewModel by viewModels<CustomerProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog = AlertDialogCustom(requireActivity())
        val idCustomer = arguments?.getString("customer-profile-id").toString()
        viewModel.getCustomerData(id = idCustomer)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.getCustomer.observe(viewLifecycleOwner) { response ->
            response.user?.let { setupView(customer = it) }
        }

        binding.logout.setOnClickListener {
            alertDialog.apply {
                create(
                    title = getString(R.string.logout_confirmation),
                    message = getString(R.string.logout_confirmation_message),
                    hasNegativeBtn = true,
                    onPositiveButtonClick = {
                        viewModel.logout()
                    },
                    onNegativeButtonClick = { /* Do Nothing */ }
                )
                show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupView(customer: User) {
        binding.apply {
            val formattedId = "Your ID : " + customer.idCustomer.chunked(4).joinToString(" ")
            profileId.text = formattedId
            profileName.text = customer.nama
            profileRole.text = "Total Point : ${customer.poin} pt"
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            profileInfoWrapper.visibility = if (isLoading) View.GONE else View.VISIBLE
            logout.visibility = if (isLoading) View.GONE else View.VISIBLE
        }
    }
}