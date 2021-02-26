package id.itborneo.wonderfullindonesia.data

data class CityModel(
    val name: String,
    val description: String,
    val image: Int,
    val isfavorite: Boolean = false
)
