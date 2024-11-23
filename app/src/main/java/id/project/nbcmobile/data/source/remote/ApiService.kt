package id.project.nbcmobile.data.source.remote

import id.project.nbcmobile.data.source.remote.response.GetUserDataResponse
import id.project.nbcmobile.data.source.remote.response.LoginResponse
import id.project.nbcmobile.data.source.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
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
    ) : RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse

    @GET("get-customer/{id}")
    suspend fun getCustomerData(
        @Path("id") id: String
    ) : GetUserDataResponse

    @GET("get-pegawai/{id}")
    suspend fun getPegawaiData(
        @Path("id") id: Int
    ) : GetUserDataResponse

    // TODO - Add the rest of API Endpoints here :

}