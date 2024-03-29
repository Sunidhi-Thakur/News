package com.example.news

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context, val articles: List<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.newsTitle.text = article.title
        //holder.newsTitle.setBackgroundColor(Color.parseColor(MyColorsList.getColor2()))
        holder.newsDescription.text = article.description
        //holder.newsDescription.setBackgroundColor(Color.parseColor(MyColorsList.getColor3()))
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener{
            Toast.makeText(context, article.title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("URL", article.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = articles.size


    class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.NewsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.NewsTitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.NewsDescription)
    }

}