package id.project.nbcmobile.view.guest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.project.nbcmobile.data.repository.Repository
import id.project.nbcmobile.data.source.remote.response.JadwalDokterResponse
import id.project.nbcmobile.data.source.remote.response.ListPerawatanResponse
import id.project.nbcmobile.data.source.remote.response.ListProdukResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeGuestViewModel(private val repository: Repository) : ViewModel() {
    private val _getListProduk = MutableLiveData<ListProdukResponse>()
    val getListProduk: LiveData<ListProdukResponse> = _getListProduk

    private val _getListPerawatan = MutableLiveData<ListPerawatanResponse>()
    val getListPerawatan: LiveData<ListPerawatanResponse> = _getListPerawatan

    private val _getJadwalDokter = MutableLiveData<JadwalDokterResponse>()
    val getJadwalDokter: LiveData<JadwalDokterResponse> = _getJadwalDokter

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getListProduk() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getListProduk()
                _getListProduk.value = response

            } catch (e: HttpException) {
                Log.e(TAG, "onFailure: ${e.message()}")
            }
            _isLoading.value = false
        }
    }

    fun getListPerawatan() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getListPerawatan()
                _getListPerawatan.value = response

            } catch (e: HttpException) {
                Log.e(TAG, "onFailure: ${e.message()}")
            }
            _isLoading.value = false
        }
    }

    fun getJadwalDokter() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getJadwalDokter()
                _getJadwalDokter.value = response

            } catch (e: HttpException) {
                Log.e(TAG, "onFailure: ${e.message()}")
            }
            _isLoading.value = false
        }
    }

    companion object {
        private const val TAG = "HomeGuestViewModel"
    }
}