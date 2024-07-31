package com.example.newsify

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


// https://newsapi.org/v2/top-headlines?country=in&apiKey=7e7b687027174f648ecedc22cddb85d6
// https://newsapi.org/v2/everything?q=apple&from=2023-09-13&to=2023-09-13&sortBy=popularity&apiKey=7e7b687027174f648ecedc22cddb85d6

const val BASE_URL = "https://newsapi.org/"                  // common portion
const val API_KEY = "7e7b687027174f648ecedc22cddb85d6"       // common portion

interface NewsInterface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country") country : String, @Query( "page" ) page: Int) : Call<news>

    // https://newsapi.org/v2/top-headlines?apiKey=7e7b687027174f648ecedc22cddb85d6&country=in&page=1

}

object NewsService {
    val newsInstance: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}