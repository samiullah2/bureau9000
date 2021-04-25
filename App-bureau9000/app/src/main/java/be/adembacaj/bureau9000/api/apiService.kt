package be.adembacaj.bureau9000.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


private const val BASE_URL_RISICOBESCHRIJVING = "https://adembacaj.be/bureau9000/Rest-api/risicobeschrijving"


private val retrofit_Risicobeschrijving = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_RISICOBESCHRIJVING)
    .build()

interface ApiService {
    @GET("read.php")
    fun getRisicos(): Call<Risicobeschrijving>

//    @GET("login.php")
//    fun checklogin(
//        @Query("email") email: String,
//        @Query("password") password: String) : Call<Participant>

}


object Api_Risicobeschrijving {
    val retrofitService : ApiService by lazy{
        retrofit_Risicobeschrijving.create(ApiService::class.java)
    }
}