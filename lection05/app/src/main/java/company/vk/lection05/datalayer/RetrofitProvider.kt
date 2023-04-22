package company.vk.lection05.datalayer

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider() {
    private val gson = GsonBuilder().create()
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder().apply {
        addNetworkInterceptor(loggingInterceptor)
    }.build()

    fun provide(): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttpClient)
            baseUrl("https://cataas.com")
        }.build()
    }
}
