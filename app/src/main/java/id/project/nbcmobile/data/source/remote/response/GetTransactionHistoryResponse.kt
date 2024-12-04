package id.project.nbcmobile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetTransactionHistoryResponse(

    @field:SerializedName("transaksi")
    val transaksi: List<TransaksiItem>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class TransaksiItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("no_transaksi")
    val noTransaksi: String?,

    @field:SerializedName("tanggal_transaksi")
    val tanggalTransaksi: String,

    @field:SerializedName("jenis_transaksi")
    val jenisTransaksi: String,

    @field:SerializedName("status_pembayaran")
    val statusPembayaran: String,

    @field:SerializedName("diskon")
    val diskon: Int,

    @field:SerializedName("poin_earned")
    val poinEarned: Int,

    @field:SerializedName("total_harga")
    val totalHarga: Int,

    @field:SerializedName("customer_id")
    val customerId: Int? = null,

    @field:SerializedName("kasir_id")
    val kasirId: Int? = null,

    @field:SerializedName("customer_service_id")
    val customerServiceId: Int? = null,

    @field:SerializedName("beautician_id")
    val beauticianId: Int? = null,

    @field:SerializedName("dokter_id")
    val dokterId: Int? = null,

    @field:SerializedName("ruangan_id")
    val ruanganId: Int? = null,

    @field:SerializedName("promo_id")
    val promoId: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,
)
