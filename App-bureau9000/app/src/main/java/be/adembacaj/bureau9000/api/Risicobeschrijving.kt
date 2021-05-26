package be.adembacaj.bureau9000.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Risicobeschrijving(
    val id: String,
    val beschrijving: String,
    val waarde: String,
) : Parcelable

{
    override fun toString(): String = beschrijving
}
