package id.project.nbcmobile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
	@SerializedName("user_type")
	val userType: String,  // e.g., "User" or "pegawai"

	@SerializedName("message")
	val message: String,

	@SerializedName("status")
	val status: String,

	@SerializedName("token")
	val token: String,

	@SerializedName("pegawai")
	val pegawai: Pegawai? = null, // Nullable to handle cases when it's not a pegawai

	@SerializedName("user")
	val user: User? = null // Nullable to handle cases when it's not a customer
)

data class User(
	@SerializedName("id_customer")
	val idCustomer: String,

	@SerializedName("created_at")
	val createdAt: Any? = null,

	@SerializedName("email_verified_at")
	val emailVerifiedAt: Any? = null,

	@SerializedName("alamat")
	val alamat: String,

	@SerializedName("poin")
	val poin: Int,

	@SerializedName("nama")
	val nama: String,

	@SerializedName("updated_at")
	val updatedAt: Any? = null,

	@SerializedName("id")
	val id: Int,

	@SerializedName("no_telp")
	val noTelp: String,

	@SerializedName("jenis_kelamin")
	val jenisKelamin: String,

	@SerializedName("alergi")
	val alergi: String,

	@SerializedName("email")
	val email: String,

	@SerializedName("tanggal_lahir")
	val tanggalLahir: String
)

data class Pegawai(
	@SerializedName("id")
	val id: Int,

	@SerializedName("nama")
	val nama: String,

	@SerializedName("email")
	val email: String,

	@SerializedName("jenis_kelamin")
	val jenisKelamin: String,

	@SerializedName("alamat")
	val alamat: String,

	@SerializedName("no_telp")
	val noTelp: String,

	@SerializedName("status")
	val status: String,

	@SerializedName("password")
	val password: String,

	@SerializedName("role")
	val role: String,

	@SerializedName("created_at")
	val createdAt: Any? = null,

	@SerializedName("updated_at")
	val updatedAt: Any? = null
)