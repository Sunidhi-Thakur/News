package com.example.news

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.news.databinding.ActivityMainBinding
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    lateinit var binding: ActivityMainBinding
    private var articles = mutableListOf<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NewsAdapter(this@MainActivity, articles)
        binding.newsList.adapter = adapter

        // Progress Bar

      //  binding.newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
                binding.container.setBackgroundColor(Color.parseColor(MyColorsList.getColor1()))
            }
        })
        binding.newsList.layoutManager = layoutManager

        getNews()
    }

    private fun getNews() {
       val news: Call<News> = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news: News? = response.body()
                if (news != null) {
                    Log.d("SUNIDHI", news.toString())
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged() //Re-render data
                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("SUNIDHI", "Error")
            }

        })
    }
}