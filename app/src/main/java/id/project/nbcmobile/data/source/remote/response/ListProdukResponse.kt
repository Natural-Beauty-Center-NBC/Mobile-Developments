package id.project.nbcmobile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListProdukResponse(

	@field:SerializedName("produks")
	val produks: List<ProduksItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class ProduksItem(

	@field:SerializedName("ukuran")
	val ukuran: Int,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("harga")
	val harga: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("stok")
	val stok: Int
)
