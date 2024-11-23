package id.project.nbcmobile.view.pegawai.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import id.project.nbcmobile.R
import id.project.nbcmobile.data.source.remote.response.Pegawai
import id.project.nbcmobile.databinding.FragmentProfilePegawaiBinding
import id.project.nbcmobile.view.components.customView.AlertDialogCustom
import id.project.nbcmobile.view.components.utils.ViewModelFactory

class PegawaiProfileFragment : Fragment() {
    private var _binding: FragmentProfilePegawaiBinding? = null
    private val binding get() = _binding!!

    private lateinit var alertDialog: AlertDialogCustom
    private val viewModel by viewModels<PegawaiProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilePegawaiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog = AlertDialogCustom(requireActivity())
        val idPegawai = arguments?.getInt("pegawai-profile-id")
        viewModel.getPegawaiData(id = idPegawai!!)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.getPegawai.observe(viewLifecycleOwner) { response ->
            response.pegawai?.let { setupView(pegawai = it) }
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

    private fun setupView(pegawai: Pegawai) {
        binding.apply {
            profileName.text = pegawai.nama
            profileRole.text = "as a ${pegawai.role}"
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