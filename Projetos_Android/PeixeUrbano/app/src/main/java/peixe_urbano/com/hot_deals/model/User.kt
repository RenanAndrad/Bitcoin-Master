package peixe_urbano.com.hot_deals.model

data class User(
    var id: Long = 0L,
    var name: String = "",
    var email: String = "",
    var picture: DataPicture? = null,
    var gender: String = "",
    var success: Boolean = false

)
