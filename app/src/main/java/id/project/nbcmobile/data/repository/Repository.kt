package id.project.nbcmobile.data.repository

import id.project.nbcmobile.data.model.UserModel
import id.project.nbcmobile.data.preference.UserPreference
import id.project.nbcmobile.data.source.remote.ApiService
import id.project.nbcmobile.data.source.remote.response.DetailTransactionResponse
import id.project.nbcmobile.data.source.remote.response.ErrorResponse
import id.project.nbcmobile.data.source.remote.response.GetRuanganResponse
import id.project.nbcmobile.data.source.remote.response.GetTransactionHistoryResponse
import id.project.nbcmobile.data.source.remote.response.GetUserDataResponse
import id.project.nbcmobile.data.source.remote.response.JadwalDokterResponse
import id.project.nbcmobile.data.source.remote.response.ListPerawatanResponse
import id.project.nbcmobile.data.source.remote.response.ListProdukResponse
import id.project.nbcmobile.data.source.remote.response.LoginResponse
import id.project.nbcmobile.data.source.remote.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

class Repository(
    private val preference: UserPreference,
    private val apiService: ApiService
) {
    /* Handle application user's preferences */
    suspend fun saveSession(user: UserModel) {
        preference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }

    suspend fun logout() {
        preference.logout()
    }

    /* Handle Authentication */
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

    /* Handle Profile's Information */
    suspend fun getCustomerData(id: String): GetUserDataResponse {
        return apiService.getCustomerData(id)
    }

    suspend fun getPegawaiData(id: Int): GetUserDataResponse {
        return apiService.getPegawaiData(id)
    }

    /* Handle Customer Transaction's Data */
    suspend fun getCustomerTransactionData(id: String): GetTransactionHistoryResponse {
        return apiService.getCustomerTransactionData(id)
    }

    suspend fun getDetailTransaction(id: String): DetailTransactionResponse {
        return apiService.getDetailTransaction(id)
    }

    /* Handle Information to Guest and Customer */
    suspend fun getListProduk() : ListProdukResponse {
        return apiService.getListProduk()
    }

    suspend fun getListPerawatan() : ListPerawatanResponse {
        return apiService.getListPerawatans()
    }

    suspend fun getJadwalDokter() : JadwalDokterResponse {
        return apiService.getJadwalDokter()
    }

    /* Handle Ruangan */
    suspend fun getRuangan(id: Int) : GetRuanganResponse {
        return apiService.getRuangan(id)
    }

    suspend fun updateRuangan(idRuangan: Int, idPegawai: Int) : ErrorResponse {
        return apiService.updateRuangan(idRuangan = idRuangan, idPegawai = idPegawai)
    }
}