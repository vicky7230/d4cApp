package com.vicky7230.d4capp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicky7230.d4capp.R
import com.vicky7230.d4capp.ui.state.Category
import com.vicky7230.d4capp.ui.theme.D4cAppTheme
import com.vicky7230.d4capp.ui.theme.LightBlack
import com.vicky7230.d4capp.ui.theme.centuryOldStyleStdBold

@Composable
fun Categories(
    modifier: Modifier = Modifier,
    categories: List<Category>
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categories",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = centuryOldStyleStdBold
            )
            Spacer(Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = "See all",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline
            )
        }
        Spacer(Modifier.height(18.dp))
        LazyRow {
            items(categories) {
                CategoryItem(it)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        modifier = Modifier
            .padding(end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(category.imageRes),
                contentDescription = "category_item",
                contentScale = ContentScale.Fit
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(category.title, color = Color.White, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategories() {
    D4cAppTheme {
        Box(Modifier.background(color = LightBlack)) {
            Categories(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 20.dp),
                categories = listOf(
                    Category("Cleaners", R.drawable.category_sample),
                    Category("Toner", R.drawable.category_sample),
                    Category("Serums", R.drawable.category_sample),
                    Category("Moisturisers", R.drawable.category_sample),
                    Category("Sunscreen", R.drawable.category_sample),
                    Category("Sunglasses", R.drawable.category_sample)
                )
            )
        }
    }
}

@Preview()
@Composable
fun PreviewCategoryItem() {
    D4cAppTheme {
        Box(Modifier.background(color = LightBlack)) {
            CategoryItem(
                category = Category(
                    title = "Cleaners",
                    imageRes = R.drawable.category_sample
                )
            )
        }
    }
}