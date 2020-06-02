package com.example.exianewsv3.helpers

import com.example.exianewsv3.models.Article
import java.util.*

class NewsStore {
    private val newsModels: List<Article> =
        ArrayList<Article>()


    fun getNewsModels(): List<Article>? {
        return newsModels
    }

    /*fun setNewsModels(newsModels: List<Article?>) {
        NewsStore.newsModels = newsModels
    }*/
}