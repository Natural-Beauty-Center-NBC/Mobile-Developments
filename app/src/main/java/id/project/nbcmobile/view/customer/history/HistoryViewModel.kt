package id.project.nbcmobile.view.customer.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.project.nbcmobile.data.repository.Repository
import id.project.nbcmobile.data.source.remote.response.DetailTransactionResponse
import id.project.nbcmobile.data.source.remote.response.GetTransactionHistoryResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HistoryViewModel(private val repository: Repository) : ViewModel() {
    private val _getCustomerTransaction = MutableLiveData<GetTransactionHistoryResponse>()
    val getUserTransaction: LiveData<GetTransactionHistoryResponse> = _getCustomerTransaction

    private val _getDetailTransaction = MutableLiveData<DetailTransactionResponse>()
    val getDetailTransaction: LiveData<DetailTransactionResponse> = _getDetailTransaction

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getTransactionHistoryData(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getCustomerTransactionData(id)
                _getCustomerTransaction.value = response

            } catch (e: HttpException) {
                Log.e(TAG, "onFailure: ${e.message()}")
            }
            _isLoading.value = false
        }
    }

    fun getDetailTransaction(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getDetailTransaction(id)
                _getDetailTransaction.value = response

            } catch (e: HttpException) {
                Log.e(TAG, "onFailure: ${e.message()}")
            }
            _isLoading.value = false
        }
    }

    companion object {
        private const val TAG = "HistoryViewModel"
    }
}