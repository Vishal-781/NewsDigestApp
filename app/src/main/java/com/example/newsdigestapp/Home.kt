package com.example.newsdigestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

//b27718061413465a95a7dfc3efc7eef2

class Home : AppCompatActivity(), NewsItemClicked{
    private  lateinit var mAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

       val idrRecyclerView: RecyclerView=findViewById(R.id.idrRecyclerview)
        idrRecyclerView.layoutManager=LinearLayoutManager(this)
        val idr2RecyclerView: RecyclerView=findViewById(R.id.idr2Recyclerview)
        idr2RecyclerView.layoutManager=LinearLayoutManager(this)
        fetchData()
        val mAdapter= NewsAdapter(this)
        idr2RecyclerView.adapter=mAdapter
    }

    private  fun fetchData() {
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=science&excludeDomains=stackOverflow&sortBy=publishedAt&language=en&apiKey=b27718061413465a95a7dfc3efc7eef2"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
                  val newsJsonArray=it.getJSONArray("articles")
                  val newsArray = ArrayList<News>()
                for(i in 0 until newsJsonArray.length()){
                    val newsJasonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJasonObject.getString("title"),
                        newsJasonObject.getString("author"),
                                newsJasonObject.getString("url"),
                        newsJasonObject.getString("imageUrl"),
                        newsJasonObject.getString("cnt")

                        )
                    newsArray.add(news)
                    Log.d("title",news.title)

                }
              mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun Onitemclicked(item: News) {
        TODO("Not yet implemented")
    }

}