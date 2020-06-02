package com.example.exianewsv3.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exianewsv3.R
import com.example.exianewsv3.helpers.formNewsApiDate
import com.example.exianewsv3.models.Article
import com.example.exianewsv3.views.NewsDetailActivity
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val context: Context,private val data: List<Article>): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return NewsHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val item = data.get(position)
        val formatDateNews = item.publishedAt.let { formNewsApiDate(it) }
        holder.txtNewsTitle.text = item.title
        holder.txtNewsTime.text = "$formatDateNews"
        holder.txtNewsContent.text = item.description

        Glide.with(holder.imgNews.context)
            .load(item.urlToImage)
            .centerCrop()
            .into(holder.imgNews)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,NewsDetailActivity::class.java)
            intent.putExtra("url_news",item.url)

            context.startActivity(intent)
        }

    }

    inner class NewsHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val imgNews = itemView.img_news
        val txtNewsTitle = itemView.txt_news_title
        val txtNewsTime =itemView.txt_news_time
        val txtNewsContent =itemView.txt_news_content

    }

}