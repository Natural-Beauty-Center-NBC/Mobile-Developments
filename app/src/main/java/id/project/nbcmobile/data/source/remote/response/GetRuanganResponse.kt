package id.project.nbcmobile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetRuanganResponse(

	@field:SerializedName("data")
	val data: DataRuangan,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Ruangan(

	@field:SerializedName("no_ruangan")
	val noRuangan: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("assign_to")
	val assignTo: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("pegawai")
	val pegawai: Pegawai,

	@field:SerializedName("status")
	val status: String
)

data class ListRuanganItem(

	@field:SerializedName("no_ruangan")
	val noRuangan: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("assign_to")
	val assignTo: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("status")
	val status: String
)

data class DataRuangan(

	@field:SerializedName("current")
	val current: Current,

	@field:SerializedName("listRuangan")
	val listRuangan: List<ListRuanganItem>
)

data class Current(

	@field:SerializedName("ruangan")
	val ruangan: Ruangan? = null,

	@field:SerializedName("assign_to")
	val assignTo: String? = null
)
