package com.example.newsapp

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf

fun shareNews(context: Context, url: String?) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, "Share Via")
    ContextCompat.startActivity(context, shareIntent, bundleOf())
}