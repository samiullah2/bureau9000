package be.adembacaj.bureau9000.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Project(
        val id: Int = Int.MIN_VALUE,
        val naam: String,
) : Parcelable

{
    override fun toString(): String = "$id - $naam"
}
