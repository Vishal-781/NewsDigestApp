package com.example.newsdigestapp

import android.icu.text.CaseMap
import retrofit2.http.Url

data class News(
    val title: String,
    val author: String,
    val url:String,
    val imageUrl: String,
    val cnt: String
)
