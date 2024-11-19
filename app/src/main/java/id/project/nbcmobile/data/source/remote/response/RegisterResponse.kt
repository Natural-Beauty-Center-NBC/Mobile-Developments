package id.project.nbcmobile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("user")
    val user: Customer? = null
)

data class Customer(

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("id_customer")
    val idCustomer: String,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("no_telp")
    val noTelp: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("jenis_kelamin")
    val jenisKelamin: String,

    @field:SerializedName("alergi")
    val alergi: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("tanggal_lahir")
    val tanggalLahir: String,

    @field:SerializedName("alamat")
    val alamat: String
)
