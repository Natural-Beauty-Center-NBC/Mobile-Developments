package id.project.nbcmobile.view.room_management

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import id.project.nbcmobile.R
import id.project.nbcmobile.databinding.FragmentHomeBinding
import id.project.nbcmobile.databinding.FragmentRoomManagementBinding
import id.project.nbcmobile.view.home.HomeViewModel

class RoomManagementFragment : Fragment() {

    private var _binding: FragmentRoomManagementBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(RoomManagementViewModel::class.java)

        _binding = FragmentRoomManagementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}