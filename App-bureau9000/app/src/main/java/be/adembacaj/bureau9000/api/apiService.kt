package be.adembacaj.bureau9000.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

///////////////////////////////////////////////////////////////
////////////////////          BASE URLS            ////////////
///////////////////////////////////////////////////////////////

private const val BASE_URL_PROJECT = "https://adembacaj.be/bureau9000/Rest-api/project/"
private const val BASE_URL_RISICOBESCHRIJVING = "https://adembacaj.be/bureau9000/Rest-api/risicobeschrijving/"
private const val BASE_URL_OPDRACHTGEVER = "https://adembacaj.be/bureau9000/Rest-api/opdrachtgever/"
private const val BASE_URL_GEBOUW = "https://adembacaj.be/bureau9000/Rest-api/gebouw/"
private const val BASE_URL_GEBOUWRISICO = "https://adembacaj.be/bureau9000/Rest-api/gebouwRisico/"
private const val BASE_URL_GEBOUWRISICOGEBOUW = "https://adembacaj.be/bureau9000/Rest-api/gebouwRisicoGebouw/"


///////////////////////////////////////////////////////////////
////////////////////          BUILDERS            ////////////
///////////////////////////////////////////////////////////////


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

private val retrofit_Gebouw = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_GEBOUW)
    .build()

private val retrofit_GebouwRisico = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_GEBOUWRISICO)
    .build()

private val retrofit_GebouwRisicoGebouw = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_GEBOUWRISICOGEBOUW)
    .build()

///////////////////////////////////////////////////////////////
////////////////////          INTERFACES            ////////////
///////////////////////////////////////////////////////////////


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

    @GET("readOneWholeProject.php")
    fun getOneWholeProject(
        @Query("id") id : Int): Call<ProjectEnOpdrachtgever>

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

interface ApiServiceGebouw {
    @POST("readByProjectId.php")
    fun getGebouwenByProjectId(
        @Query("projectId") id : Int): Call<ArrayList<Gebouw>>

    @Headers("Content-Type: application/json")
    @POST("create.php")
    fun addGebouw(@Body gebouwData: Gebouw): Call<Gebouw>
}

interface ApiServiceGebouwRisico {
    @GET("read.php")
    fun getRisicos(): Call<ArrayList<GebouwRisico>>

    @GET("readOne.php")
    fun getOneRisico(): Call<GebouwRisico>
}

interface ApiServiceGebouwRisicoGebouw {
    @POST("create.php")
    fun addGebouwRisicoGebouw(@Body gebouwRisicoGebouwData: GebouwRisicoGebouw): Call<GebouwRisicoGebouw>

    @POST("readByGebouwId.php")
    fun getrisicosByGebouw(
        @Query("gebouwId") id : Int): Call<ArrayList<GebouwRisico>>
}


///////////////////////////////////////////////////////////////
////////////////////          OBJECTS              ////////////
///////////////////////////////////////////////////////////////

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

object Api_Gebouw {
    val retrofitService : ApiServiceGebouw by lazy{
        retrofit_Gebouw.create(ApiServiceGebouw::class.java)
    }
}

object Api_GebouwRisico {
    val retrofitService : ApiServiceGebouwRisico by lazy{
        retrofit_GebouwRisico.create(ApiServiceGebouwRisico::class.java)
    }
}

object Api_GebouwRisicoGebouw {
    val retrofitService : ApiServiceGebouwRisicoGebouw by lazy{
        retrofit_GebouwRisicoGebouw.create(ApiServiceGebouwRisicoGebouw::class.java)
    }
}