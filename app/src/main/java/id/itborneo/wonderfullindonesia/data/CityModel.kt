package id.itborneo.wonderfullindonesia.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CityModel(
    val id: String,
    val name: String,
    val description: String,
    val image: Int,
    var isfavorite: Boolean = false
) : Parcelable
