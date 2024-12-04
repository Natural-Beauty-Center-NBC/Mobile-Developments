package id.project.nbcmobile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailTransactionResponse(

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class Transaksi(

    @field:SerializedName("kasir_id")
    val kasirId: Int? = null,

    @field:SerializedName("customer_service_id")
    val customerServiceId: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("total_harga")
    val totalHarga: Int,

    @field:SerializedName("promo_id")
    val promoId: Int? = null,

    @field:SerializedName("beautician_id")
    val beauticianId: Int? = null,

    @field:SerializedName("no_transaksi")
    val noTransaksi: String,

    @field:SerializedName("tanggal_transaksi")
    val tanggalTransaksi: String,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("status_pembayaran")
    val statusPembayaran: String,

    @field:SerializedName("dokter_id")
    val dokterId: Int? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("jenis_transaksi")
    val jenisTransaksi: String,

    @field:SerializedName("poin_earned")
    val poinEarned: Int,

    @field:SerializedName("customer_id")
    val customerId: Int? = null,

    @field:SerializedName("diskon")
    val diskon: Int,

    @field:SerializedName("ruangan_id")
    val ruanganId: Int? = null
)

data class DetailProdukItem(

    @field:SerializedName("jumlah_pembelian")
    val jumlahPembelian: Int,

    @field:SerializedName("transaksi_id")
    val transaksiId: Int,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("produk")
    val produk: ProduksItem,

    @field:SerializedName("sub_total")
    val subTotal: Int,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("produk_id")
    val produkId: Int
)

data class DetailPerawatanItem(

    @field:SerializedName("jumlah_pembelian")
    val jumlahPembelian: Int,

    @field:SerializedName("transaksi_id")
    val transaksiId: Int,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("sub_total")
    val subTotal: Int,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("jumlah_tukar_point")
    val jumlahTukarPoint: Int,

    @field:SerializedName("perawatan_id")
    val perawatanId: Int,

    @field:SerializedName("perawatan")
    val perawatan: PerawatansItem
)

data class Data(

    @field:SerializedName("detailProduk")
    val detailProduk: List<DetailProdukItem>,

    @field:SerializedName("transaksi")
    val transaksi: Transaksi,

    @field:SerializedName("detailPerawatan")
    val detailPerawatan: List<DetailPerawatanItem>
)
