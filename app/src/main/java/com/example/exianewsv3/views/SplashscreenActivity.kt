package com.example.exianewsv3.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exianewsv3.R
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Timer("splashGone", true).schedule(3000) {
            startActivity<MainActivity>()
            finish()
        }
    }
}
