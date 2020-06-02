package com.example.exianewsv3.views

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exianewsv3.R
import com.example.exianewsv3.adapter.NewsAdapter
import com.example.exianewsv3.helpers.LoadingIndicator
import com.example.exianewsv3.models.Article
import com.example.exianewsv3.models.ResponseNews
import com.example.exianewsv3.networkUtils.NetworkConfig
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var loadingIndicator :LoadingIndicator?=null
    private lateinit var dataNews:List<Article?>
    private val keyAPI ="3aa66a534dbe4bdea05f7a067f7a5fec"
    private val nationality="id"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingIndicator= LoadingIndicator(this)

        loadingIndicator?.showLoadingIndicator()
        loadDataArticles()
    }

    private fun loadDataArticles() {

        if (isConnectOrNot()){
            val call: Call<ResponseNews?>? = NetworkConfig.callApiService().getArticles(nationality,keyAPI)
            call?.enqueue(object : Callback<ResponseNews?> {
                override fun onResponse(call: Call<ResponseNews?>, response: Response<ResponseNews?>) {
                    loadingIndicator?.dismissLoading()
                    showNewsApiSnack()
                    if (response.isSuccessful()) {
                        Log.e("_logResponse1st", "" + response.toString())
                        dataNews = response.body()?.articles!!

                        val showAdapter = NewsAdapter(this@MainActivity, dataNews as List<Article>)
                        list_of_news.adapter = showAdapter
                        //NewsStore.setNewsModels(responseArticles.getArticles())
                        //newsAdapter = NewsAdapter(responseArticles.getArticles())
                        //listOfNews.setAdapter(newsAdapter)
                    } else {
                        loadingIndicator?.dismissLoading()
                        when (response.code()) {
                            401 -> Toast.makeText(
                                this@MainActivity,
                                "Unauthorized error",
                                Toast.LENGTH_SHORT
                            ).show()
                            403 ->Toast.makeText(
                                this@MainActivity,
                                "Forbidden",
                                Toast.LENGTH_SHORT
                            ).show()
                            404 ->Toast.makeText(
                                this@MainActivity,
                                "Not found",
                                Toast.LENGTH_SHORT
                            ).show()
                            500 -> {
                                //Toast.makeText(MainActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                                iv_no_history.setVisibility(View.VISIBLE)
                                txt_no_data.setVisibility(View.VISIBLE)
                            }

                        }

                    }
                }

                override fun onFailure(
                    call: Call<ResponseNews?>,
                    t: Throwable
                ) {
                    loadingIndicator?.dismissLoading()
                    t.message
                }
            })
        }else{
            Toast.makeText(this,"can't connect to internet",Toast.LENGTH_SHORT).show()
        }

    }

    fun isConnectOrNot():Boolean{
        val connect: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo.isConnected
    }

    private fun showNewsApiSnack() {
        Snackbar.make(main_layout, "Powered by Newsapi.org", Snackbar.LENGTH_LONG)
            .setAction("Visit") { loadToTheSourceApi() }.show()
    }

    private fun loadToTheSourceApi() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://newsapi.org")))
    }
}
