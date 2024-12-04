package id.project.nbcmobile.data.source.remote

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
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    /* Handle Authentication */
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("nama") name: String,
        @Field("email") email: String,
        @Field("tanggal_lahir") dateOfBirth: String,
        @Field("jenis_kelamin") gender: String,
        @Field("alamat") address: String,
        @Field("no_telp") numPhone: String,
        @Field("alergi") allergy: String?,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    /* Handle Profile's Information */
    @GET("get-customer/{id}")
    suspend fun getCustomerData(
        @Path("id") id: String
    ): GetUserDataResponse

    @GET("get-pegawai/{id}")
    suspend fun getPegawaiData(
        @Path("id") id: Int
    ): GetUserDataResponse

    /* Handle Customer Transaction's Data */
    @GET("get-customer-transaction-history/{id}")
    suspend fun getCustomerTransactionData(
        @Path("id") id: String
    ): GetTransactionHistoryResponse

    @GET("get-customer-detail-transaction/{id}")
    suspend fun getDetailTransaction(
        @Path("id") id: String
    ): DetailTransactionResponse

    /* Handle Information to Guest and Customer */
    @GET("get-produks")
    suspend fun getListProduk(): ListProdukResponse

    @GET("get-perawatans")
    suspend fun getListPerawatans(): ListPerawatanResponse

    @GET("get-jadwal-dokter")
    suspend fun getJadwalDokter(): JadwalDokterResponse

    /* Handle Ruangan */
    @GET("get-ruangan/{id}")
    suspend fun getRuangan(
        @Path("id") id: Int
    ): GetRuanganResponse

    @PUT("update-ruangan/{idRuangan}/{idPegawai}")
    suspend fun updateRuangan(
        @Path("idRuangan") idRuangan: Int,
        @Path("idPegawai") idPegawai: Int
    ): ErrorResponse
}