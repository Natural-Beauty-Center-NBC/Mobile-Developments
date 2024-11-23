package id.project.nbcmobile.view.pegawai.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.project.nbcmobile.data.repository.Repository
import id.project.nbcmobile.data.source.remote.response.GetUserDataResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PegawaiProfileViewModel(private val repository: Repository) : ViewModel() {
    private val _getPegawai = MutableLiveData<GetUserDataResponse>()
    val getPegawai: LiveData<GetUserDataResponse> = _getPegawai

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun getPegawaiData(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getPegawaiData(id)
                _getPegawai.value = response

            } catch (e: HttpException) {
                Log.e(TAG, "onFailure: ${e.message()}")
            }
            _isLoading.value = false
        }
    }

    companion object {
        private const val TAG = "PegawaiProfileViewModel"
    }
}