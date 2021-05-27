package be.adembacaj.bureau9000.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GebouwRisico(
    val id: Int = Int.MIN_VALUE,
    val beschrijving: String,
    val w: Double,
    ) : Parcelable
{
    override fun toString(): String = "$id - $beschrijving : $w"
}