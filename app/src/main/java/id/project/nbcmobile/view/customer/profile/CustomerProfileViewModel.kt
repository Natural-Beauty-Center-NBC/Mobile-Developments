package id.project.nbcmobile.view.customer.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.project.nbcmobile.data.repository.Repository
import id.project.nbcmobile.data.source.remote.response.GetUserDataResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CustomerProfileViewModel(private val repository: Repository) : ViewModel() {
    private val _getCustomer = MutableLiveData<GetUserDataResponse>()
    val getCustomer: LiveData<GetUserDataResponse> = _getCustomer

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun getCustomerData(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getCustomerData(id)
                _getCustomer.value = response

            } catch (e: HttpException) {
                Log.e(TAG, "onFailure: ${e.message()}")
            }
            _isLoading.value = false
        }
    }

    companion object {
        private const val TAG = "CustomerProfileViewModel"
    }
}