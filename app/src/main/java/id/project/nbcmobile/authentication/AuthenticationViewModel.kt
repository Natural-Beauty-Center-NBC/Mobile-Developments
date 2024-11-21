package id.project.nbcmobile.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import id.project.nbcmobile.data.Repository
import id.project.nbcmobile.data.model.UserModel
import id.project.nbcmobile.data.source.remote.response.ErrorResponse
import id.project.nbcmobile.data.source.remote.response.LoginResponse
import id.project.nbcmobile.data.source.remote.response.RegisterResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthenticationViewModel(private val repository: Repository) : ViewModel() {
    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _errorResponse = MutableLiveData<ErrorResponse>()
    val errorResponse: LiveData<ErrorResponse> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.login(email = email, password = password)
                _loginResponse.value = response
                saveSession(
                    user = UserModel(
                        name = if (response.user != null) response.user.nama else response.pegawai!!.nama,
                        email = if (response.user != null) response.user.email else response.pegawai!!.email,
                        type = response.userType,
                        token = response.token,
                        isLogin = true
                    )
                )

            } catch (e: HttpException) {
                Log.e(TAG, "onFailure: ${e.message()}")
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                _errorResponse.value = errorBody
            }
            _isLoading.value = false
        }
    }

    fun register(
        name: String,
        email: String,
        dateOfBirth: String,
        gender: String,
        address: String,
        numPhone: String,
        allergy: String?,
        password: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.register(
                    name = name,
                    email = email,
                    dateOfBirth = dateOfBirth,
                    gender = gender,
                    address = address,
                    numPhone = numPhone,
                    allergy = allergy,
                    password = password
                )
                _registerResponse.value = response

            } catch (e: HttpException) {
                Log.e(TAG, "onFailure: ${e.message()}")
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                _errorResponse.value = errorBody
            }
            _isLoading.value = false
        }
    }

    companion object {
        private const val TAG = "AuthViewModel"
    }
}