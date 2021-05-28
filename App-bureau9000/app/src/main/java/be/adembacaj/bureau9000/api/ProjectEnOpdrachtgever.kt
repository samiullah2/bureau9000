package be.adembacaj.bureau9000.api

data class ProjectEnOpdrachtgever(
    val id : Int,
    val naam: String,
    val opdrachtgeverNaam : String,
    val adres: String,
    val postcode: Int,
    val gemeente: String,
    val email: String,
    val klantNaam : String
)