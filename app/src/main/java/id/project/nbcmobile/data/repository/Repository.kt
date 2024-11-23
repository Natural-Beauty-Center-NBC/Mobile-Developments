package id.project.nbcmobile.data.repository

import id.project.nbcmobile.data.model.UserModel
import id.project.nbcmobile.data.preference.UserPreference
import id.project.nbcmobile.data.source.remote.ApiService
import id.project.nbcmobile.data.source.remote.response.GetUserDataResponse
import id.project.nbcmobile.data.source.remote.response.LoginResponse
import id.project.nbcmobile.data.source.remote.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

class Repository(
    private val preference: UserPreference,
    private val apiService: ApiService
) {
    // PREFERENCES :
    suspend fun saveSession(user: UserModel) {
        preference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }

    suspend fun logout() {
        preference.logout()
    }

    // API SERVICES :
    suspend fun register(
        name: String,
        email: String,
        dateOfBirth: String,
        gender: String,
        address: String,
        numPhone: String,
        allergy: String?,
        password: String,
    ): RegisterResponse {
        return apiService.register(
            name = name,
            email = email,
            dateOfBirth = dateOfBirth,
            gender = gender,
            address = address,
            numPhone = numPhone,
            allergy = allergy ?: "Tidak Ada",
            password = password,
            passwordConfirmation = password
        )
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return apiService.login(email = email, password = password)
    }

    suspend fun getCustomerData(id: String): GetUserDataResponse {
        return apiService.getCustomerData(id)
    }

    suspend fun getPegawaiData(id: Int): GetUserDataResponse {
        return apiService.getPegawaiData(id)
    }

    // TODO - Add the rest of API Services here :
}