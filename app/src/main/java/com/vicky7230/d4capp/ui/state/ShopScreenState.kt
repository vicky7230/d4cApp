package com.vicky7230.d4capp.ui.state

import androidx.annotation.DrawableRes

data class PromoBanner(
    val title: String,
    val description: String,
    val dateRange: String
)

data class Category(
    val title: String,
    @DrawableRes val imageRes: Int
)