package com.vicky7230.d4capp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vicky7230.d4capp.ui.screens.ShopScreen
import com.vicky7230.d4capp.ui.state.PromoBanner
import com.vicky7230.d4capp.ui.theme.D4cAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            D4cAppTheme {
                ShopScreen(
                    modifier = Modifier.fillMaxSize(),
                    wishlistCount = 5,
                    cartItemCount = 3,
                    onBackClick = {},
                    banners = listOf(
                        PromoBanner("GET 20% OFF", "GET 20% OFF", "12 - 16 October"),
                        PromoBanner("GET 20% OFF", "GET 20% OFF", "12 - 16 October"),
                        PromoBanner("GET 20% OFF", "GET 20% OFF", "12 - 16 October"),
                    )
                )
            }
        }
    }
}