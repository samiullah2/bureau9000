package be.adembacaj.bureau9000.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL_PROJECT = "https://adembacaj.be/bureau9000/Rest-api/project/"
private const val BASE_URL_RISICOBESCHRIJVING = "https://adembacaj.be/bureau9000/Rest-api/risicobeschrijving/"
private const val BASE_URL_OPDRACHTGEVER = "https://adembacaj.be/bureau9000/Rest-api/opdrachtgever/"

private val retrofit_Project = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_PROJECT)
    .build()

private val retrofit_Risicobeschrijving = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_RISICOBESCHRIJVING)
    .build()

private val retrofit_Opdrachtgever = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_OPDRACHTGEVER)
    .build()

interface ApiServiceProject {
//    @GET("login.php")
//    fun checklogin(
//        @Query("email") email: String,
//        @Query("password") password: String) : Call<Participant>

    @Headers("Content-Type: application/json")
    @POST("create.php")
    fun addProject(@Body projectData: Project): Call<Project>

    @GET("read.php")
    fun getProjects(): Call<ArrayList<Project>>

    @GET("readOne.php")
    fun getOneProject(): Call<Project>

    @GET("readOneName.php")
    fun getOneProjectByName(
        @Query("naam") naam : String): Call<Project>

}

interface ApiServiceOpdrachtgever {
    @Headers("Content-Type: application/json")
    @POST("create.php")
    fun addOpdrachtgeverToProject(@Body OpdrachtgeverData: Opdrachtgever): Call<Opdrachtgever>
}

interface ApiServiceRisicobeschrijving {
    @GET("read.php")
    fun getRisicos(): Call<ArrayList<Risicobeschrijving>>
}


object Api_Project {
    val retrofitService : ApiServiceProject by lazy{
        retrofit_Project.create(ApiServiceProject::class.java)
    }
}

object Api_Opdrachtgever {
    val retrofitService : ApiServiceOpdrachtgever by lazy{
        retrofit_Opdrachtgever.create(ApiServiceOpdrachtgever::class.java)
    }
}

object Api_Risicobeschrijving {
    val retrofitService : ApiServiceRisicobeschrijving by lazy{
        retrofit_Risicobeschrijving.create(ApiServiceRisicobeschrijving::class.java)
    }
}