package id.project.nbcmobile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
	@SerializedName("user_type")
	val userType: String? = null,

	@SerializedName("message")
	val message: String? = null,

	@SerializedName("user")
	val user: User? = null,

	@SerializedName("status")
	val status: String? = null,

	@SerializedName("token")
	val token: String? = null
)

sealed class User {
	data class Customer(
		@SerializedName("id_customer")
		val idCustomer: String? = null,

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
		val email: String?,

		@SerializedName("tanggal_lahir")
		val tanggalLahir: String
	) : User()

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
	) : User()
}

