package id.project.nbcmobile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListPerawatanResponse(

    @field:SerializedName("perawatans")
    val perawatans: List<PerawatansItem>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String? = null
)

data class PerawatansItem(

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("harga")
    val harga: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("jumlah_potongan_poin")
    val jumlahPotonganPoin: Int,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("tipe")
    val tipe: String
)
