package id.project.nbcmobile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class JadwalDokterResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Shift(

	@field:SerializedName("end_at")
	val endAt: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("start_at")
	val startAt: String
)

data class JadwalItem(

	@field:SerializedName("shift")
	val shift: Shift,

	@field:SerializedName("dokter")
	val dokter: Dokter
)

data class Dokter(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("no_telp")
	val noTelp: String
)

data class DataItem(

	@field:SerializedName("hari")
	val hari: String,

	@field:SerializedName("jadwal")
	val jadwal: List<JadwalItem>
)
