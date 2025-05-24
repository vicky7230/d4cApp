package com.vicky7230.d4capp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicky7230.d4capp.R
import com.vicky7230.d4capp.ui.components.ActionItem
import com.vicky7230.d4capp.ui.components.Categories
import com.vicky7230.d4capp.ui.components.PromoBanners
import com.vicky7230.d4capp.ui.state.Category
import com.vicky7230.d4capp.ui.state.PromoBanner
import com.vicky7230.d4capp.ui.theme.D4cAppTheme
import com.vicky7230.d4capp.ui.theme.LightBlack
import com.vicky7230.d4capp.ui.theme.centuryOldStyleStdBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    wishlistCount: Int,
    cartItemCount: Int,
    onBackClick: () -> Unit,
    banners: List<PromoBanner>
) {
    Scaffold(
        containerColor = LightBlack,
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Shop",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontFamily = centuryOldStyleStdBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = LightBlack,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {
                    ActionItem(
                        imageVector = Icons.Default.Search,
                        count = 0,
                        contentDescription = "Search"
                    )

                    ActionItem(
                        modifier = Modifier.padding(start = 16.dp),
                        imageVector = Icons.Default.FavoriteBorder,
                        count = wishlistCount,
                        contentDescription = "Wishlist"
                    )

                    ActionItem(
                        modifier = Modifier.padding(start = 16.dp),
                        imageVector = Icons.Default.ShoppingCart,
                        count = cartItemCount,
                        contentDescription = "Cart"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            PromoBanners(
                modifier = Modifier.fillMaxWidth(),
                banners = banners
            )

            Categories(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 20.dp),
                categories = listOf(
                    Category("Cleaners", R.drawable.category_sample),
                    Category("Toner", R.drawable.category_sample),
                    Category("Serums", R.drawable.category_sample),
                    Category("Moisturisers", R.drawable.category_sample),
                    Category("Sunscreen", R.drawable.category_sample),
                    Category("Sunglasses", R.drawable.category_sample),
                    Category("Bodywash", R.drawable.category_sample),
                    Category("Facewash", R.drawable.category_sample)
                )
            )
            //NewProductsSection()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4
)
@Composable
fun PreviewShopScreen() {
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