package id.project.nbcmobile.view.customer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.project.nbcmobile.R
import id.project.nbcmobile.data.source.remote.response.DataItem
import id.project.nbcmobile.data.source.remote.response.PerawatansItem
import id.project.nbcmobile.data.source.remote.response.ProduksItem
import id.project.nbcmobile.databinding.FragmentHomeBinding
import id.project.nbcmobile.view.components.adapter.guest.JadwalDokterGuestAdapter
import id.project.nbcmobile.view.components.adapter.guest.ListPerawatanGuestAdapter
import id.project.nbcmobile.view.components.adapter.guest.ListProdukGuestAdapter
import id.project.nbcmobile.view.components.utils.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    private lateinit var rvProduk: RecyclerView
    private lateinit var rvPerawatan: RecyclerView
    private lateinit var rvDokter: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListProduk()
        viewModel.getListPerawatan()
        viewModel.getJadwalDokter()

        binding.apply {
            rvProduk = rvProdukHome
            rvProduk.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

            rvPerawatan = rvPerawatanHome
            rvPerawatan.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

            rvDokter = rvDokterHome
            rvDokter.layoutManager = LinearLayoutManager(requireActivity())
        }

        setupAction()
        setupView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupView() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.getListProduk.observe(viewLifecycleOwner) { response ->
            setupListProduk(response.produks.take(5))
        }

        viewModel.getListPerawatan.observe(viewLifecycleOwner) { response ->
            setupListPerawatan(response.perawatans.take(3))
        }

        viewModel.getJadwalDokter.observe(viewLifecycleOwner) { response ->
            setupJadwalDokter(response.data[0])
        }
    }

    private fun setupAction() {
        binding.apply {
            seeAllProduk.setOnClickListener {
                Toast.makeText(
                    requireActivity(),
                    "Coming Soon! Stay Tuned!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            seeAllPerawatan.setOnClickListener {
                Toast.makeText(
                    requireActivity(),
                    "Coming Soon! Stay Tuned!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            seeAllDokter.setOnClickListener {
                Toast.makeText(
                    requireActivity(),
                    "Coming Soon! Stay Tuned!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupListProduk(produks: List<ProduksItem>) {
        val adapter = ListProdukGuestAdapter()
        adapter.submitList(produks)
        rvProduk.adapter = adapter
    }

    private fun setupListPerawatan(perawatans: List<PerawatansItem>) {
        val adapter = ListPerawatanGuestAdapter()
        adapter.submitList(perawatans)
        rvPerawatan.adapter = adapter
    }

    private fun setupJadwalDokter(data: DataItem) {
        binding.titleDokter.text = getString(R.string.jadwal_dokter, data.hari)
        val adapter = JadwalDokterGuestAdapter()
        adapter.submitList(data.jadwal)
        rvDokter.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}