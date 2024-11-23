package id.project.nbcmobile.data.model

data class UserModel(
    val id: String,
    val name: String,
    val type: String,
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)
