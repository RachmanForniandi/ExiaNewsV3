package com.example.exianewsv3.helpers

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.exianewsv3.R

class LoadingIndicator(var context: Context) {

    //var context: Context? = null
    var txtViewLoading: TextView? = null
    lateinit var alertDialog: AlertDialog
    lateinit var imgLoadingIndicator: ImageView

    fun showLoadingIndicator() {
        val inflater = LayoutInflater.from(context)
        val supportView: View = inflater.inflate(R.layout.loading_indicator, null)
        txtViewLoading = supportView.findViewById(R.id.txt_view_loading)
        imgLoadingIndicator = supportView.findViewById(R.id.img_loading_indicator)

        val imgIndicator = imgLoadingIndicator
        Glide.with(context)
            .load(R.drawable.dot_loading)
            .into(imgIndicator)

        val builder = AlertDialog.Builder(context)
        builder.setView(supportView)
        alertDialog = builder.create()

        alertDialog.show()
        alertDialog.setCancelable(false)
    }

    fun dismissLoading() {
        alertDialog.dismiss()
    }
}