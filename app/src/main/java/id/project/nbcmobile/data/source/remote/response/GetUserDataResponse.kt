package id.project.nbcmobile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetUserDataResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("pegawai")
    val pegawai: Pegawai? = null, // Nullable to handle cases when it's not a pegawai

    @SerializedName("user")
    val user: User? = null // Nullable to handle cases when it's not a customer
)