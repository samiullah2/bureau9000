package be.adembacaj.bureau9000.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Opdrachtgever(
    val id: Int = Int.MIN_VALUE,
    val naam: String,
    val adres: String,
    val postcode: Int,
    val gemeente: String,
    val email: String,
    val klantNaam : String?,
    val projectId : Int
) : Parcelable

{
    override fun toString(): String = naam
}
