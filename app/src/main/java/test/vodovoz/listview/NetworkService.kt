package test.vodovoz.listview

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {



    @GET("newmobile/glavnaya/super_top.php?action=topglav")
    suspend fun getProducts(): NetworkResponse

    companion object {
        fun create(): NetworkService {
            val okHttpClient = OkHttpClient.Builder()
                .build()


            return Retrofit.Builder()
                .baseUrl("https://szorin.vodovoz.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(NetworkService::class.java)
        }
    }
}