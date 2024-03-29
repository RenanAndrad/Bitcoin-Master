package andrade.renan.com.bitcoinmaster.webservice;


import andrade.renan.com.bitcoinmaster.model.Bitcoin
import andrade.renan.com.bitcoinmaster.model.MarketPrice
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Iservice {

   @GET("charts/market-price")
   fun getMarketPrice(@Query("timespan") timespan: String): Call<MarketPrice>

   @GET("stats")
   fun getStats(): Call<Bitcoin>

}




