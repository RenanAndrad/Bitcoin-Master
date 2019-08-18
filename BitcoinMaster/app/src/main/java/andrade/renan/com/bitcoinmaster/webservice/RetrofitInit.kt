package andrade.renan.com.bitcoinmaster.webservice

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitInit {


    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.blockchain.info/charts/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    fun getMarketPriceService() = retrofit.create(Iservice::class.java)


}
