package id.project.nbcmobile.view.pegawai.room_management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.data.source.remote.response.Current
import id.project.nbcmobile.data.source.remote.response.ListRuanganItem
import id.project.nbcmobile.databinding.FragmentRoomManagementBinding
import id.project.nbcmobile.view.components.adapter.AvailableRuanganAdapter
import id.project.nbcmobile.view.components.customView.AlertDialogCustom
import id.project.nbcmobile.view.components.utils.ViewModelFactory

class RoomManagementFragment : Fragment() {
    private var _binding: FragmentRoomManagementBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvAvailableRuangans: RecyclerView
    private val viewModel by viewModels<RoomManagementViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }
    private lateinit var alertDialog: AlertDialogCustom

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomManagementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alertDialog = AlertDialogCustom(requireActivity())

        val idPegawai = arguments?.getInt("room-manage-id")
        viewModel.getRuangan(id = idPegawai!!)

        binding.apply {
            rvAvailableRuangans = rvAvailableRuangan
            rvAvailableRuangans.layoutManager = LinearLayoutManager(requireActivity())
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.getRuangan.observe(viewLifecycleOwner) { response ->
            setupCurrentRuangan(current = response.data.current, idPegawai = idPegawai)
            setupAvailableRuangan(listRuangan = response.data.listRuangan, idPegawai = idPegawai)
        }

        viewModel.errorResponse.observe(viewLifecycleOwner) { response ->
            alertDialog.apply {
                create(
                    title = "Error",
                    message = response.message.toString(),
                    hasNegativeBtn = false,
                    onPositiveButtonClick = {
                        viewModel.getRuangan(idPegawai)
                    },
                    onNegativeButtonClick = { /* Do Nothing */}
                )
                show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupCurrentRuangan(current: Current, idPegawai: Int) {
        if (current.ruangan == null) {
            binding.apply {
                errorCurrentRoom.visibility = View.VISIBLE
                currentRuangan.root.visibility = View.GONE
            }

        } else {
            binding.apply {
                errorCurrentRoom.visibility = View.GONE
                currentRuangan.apply {
                    root.visibility = View.VISIBLE
                    noRuang.text = "Ruangan ${current.ruangan.noRuangan}"
                    status.text = "Assigned to ${current.assignTo}"
                    switchAssign.isChecked = true

                    switchAssign.setOnClickListener {
                        viewModel.updateRuangan(idRuangan = current.ruangan.id, idPegawai = idPegawai)
                    }
                }
            }
        }
    }

    private fun setupAvailableRuangan(listRuangan: List<ListRuanganItem>, idPegawai: Int) {
        val adapter = AvailableRuanganAdapter()
        adapter.submitList(listRuangan)
        adapter.setOnItemClickCallback(object : AvailableRuanganAdapter.OnClickCallback {
            override fun onSwitchClicked(idRuangan: Int) {
                viewModel.updateRuangan(idRuangan = idRuangan, idPegawai = idPegawai)
            }
        })
        rvAvailableRuangans.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}