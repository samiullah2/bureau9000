package be.adembacaj.bureau9000.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GebouwRisicoGebouw(
    val id: Int = Int.MIN_VALUE,
    val gebouwId: Int,
    val gebouwRisicoId: Int,
) : Parcelable

//{
//    override fun toString(): String = "......."
//}
