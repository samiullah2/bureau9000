package be.adembacaj.bureau9000.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gebouw(
    val id: Int = Int.MIN_VALUE,
    val naam: String,
    val hoogte: Int, //in meters
    val adres: String,
    val postcode: Int,
    val gemeente: String,
    val functie: String,
    val projectId: Int,

) : Parcelable
{
    override fun toString(): String = "$id - $naam $functie"
}

