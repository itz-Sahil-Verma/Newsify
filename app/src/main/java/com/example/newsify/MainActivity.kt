 package com.example.newsify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class MainActivity : AppCompatActivity() {

     lateinit var adapter: NewsAdapter
     lateinit var newsList: RecyclerView
     private var articles = mutableListOf<Article>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsList = findViewById(R.id.newsList)

        adapter = NewsAdapter(this@MainActivity, articles)
        newsList.adapter = adapter
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        getNews()



    }

    private fun getNews(){
        val news = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object: Callback<news>{
            override fun onResponse(call: Call<news>, response: Response<news>) {
                val news: news? = response.body()
                if(news != null){


                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<news>, t: Throwable) {
                TODO("Error in Fetching News")
            }
        })
    }
}