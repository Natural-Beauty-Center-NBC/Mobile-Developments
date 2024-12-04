package id.project.nbcmobile.view.pegawai.room_management

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import id.project.nbcmobile.data.repository.Repository
import id.project.nbcmobile.data.source.remote.response.ErrorResponse
import id.project.nbcmobile.data.source.remote.response.GetRuanganResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RoomManagementViewModel(private val repository: Repository) : ViewModel() {
    private val _getRuangan = MutableLiveData<GetRuanganResponse>()
    val getRuangan: LiveData<GetRuanganResponse> = _getRuangan

    private val _errorResponse = MutableLiveData<ErrorResponse>()
    val errorResponse: LiveData<ErrorResponse> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getRuangan(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getRuangan(id)
                _getRuangan.value = response

            } catch (e: HttpException) {
                Log.e(TAG, "onFailure: ${e.message()}")
            }
            _isLoading.value = false
        }
    }

    fun updateRuangan(idRuangan: Int, idPegawai: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.updateRuangan(idRuangan = idRuangan, idPegawai = idPegawai)
                _getRuangan.value = repository.getRuangan(idPegawai)

            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody =
                    Gson().fromJson(jsonInString, ErrorResponse::class.java)
                _errorResponse.value = errorBody
                Log.e("TTTT", errorBody.toString())
            }
            _isLoading.value = false
        }
    }

    companion object {
        private const val TAG = "RoomManagementViewModel"
    }
}