package com.example.exianewsv3.views

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exianewsv3.R
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    companion object{
        val key_url ="url_news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val index = intent.getStringExtra(key_url)

        Log.d("url",index)

        if (index != null) {
            updateNewsDetail(index)
        } else {
            Toast.makeText(this, "Sorry detail of news failed to load", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateNewsDetail(index: String) {
        progress_horizontal.visibility = View.VISIBLE
        news_detail_webview.setWebViewClient(object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                view.loadUrl(url)

                return super.shouldOverrideUrlLoading(view, url)
            }

            /*override fun onPageStarted(
                view: WebView,
                url: String,
                favicon: Bitmap
            ) {
               // progress_horizontal.setVisibility(View.VISIBLE)
            }*/

            override fun onPageFinished(view: WebView, url: String) {
                progress_horizontal.setVisibility(View.GONE)
            }

           /* override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                progress_horizontal.setVisibility(View.GONE)
                Toast.makeText(
                    this@NewsDetailActivity,
                    "Error in loading webpage",
                    Toast.LENGTH_SHORT
                ).show()

//sbntar mas
            }*/

        })
        news_detail_webview.getSettings().setJavaScriptEnabled(true)

        news_detail_webview.loadUrl(index)
        //eh running dmna mas ? hehe
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
