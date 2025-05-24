package com.vicky7230.d4capp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.vicky7230.d4capp.R
import com.vicky7230.d4capp.ui.state.PromoBanner
import com.vicky7230.d4capp.ui.theme.D4cAppTheme
import com.vicky7230.d4capp.ui.theme.LemonGreen
import kotlinx.coroutines.delay

@Composable
fun PromoBanners(
    modifier: Modifier = Modifier,
    banners: List<PromoBanner>
) {
    val lazyListState = rememberLazyListState()
    val snappingFlingBehavior = rememberSnapFlingBehavior(lazyListState)

    var currentIndex by remember { mutableStateOf(0) }

    // Auto-scroll every 3s and track index
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextIndex = (currentIndex + 1) % banners.size
            lazyListState.animateScrollToItem(nextIndex)
            currentIndex = nextIndex
        }
    }

    Box {
        LazyRow(
            state = lazyListState,
            modifier = modifier.background(Color.Transparent),
            flingBehavior = snappingFlingBehavior
        ) {
            items(banners) { banner ->
                Banner(banner = banner)
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 54.dp, bottom = 8.dp)
        ) {
            repeat(banners.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(32.dp)
                        .height(9.dp)
                        .clip(RoundedCornerShape(50))
                        .background(
                            color = if (index == currentIndex) LemonGreen else Color.Black
                        )
                )
            }
        }
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
    banner: PromoBanner
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = modifier
            .width(screenWidth)
            .height(250.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (title, description, date, icon) = createRefs()
            Text(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, 48.dp)
                    start.linkTo(parent.start, 48.dp)
                },
                text = banner.title,
                color = Color.White,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(title.bottom, 10.dp)
                    start.linkTo(parent.start, 48.dp)
                },
                text = banner.description,
                color = Color.White.copy(alpha = 0.7f)
            )
            Text(
                text = banner.dateRange,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(date) {
                        top.linkTo(description.bottom)
                        start.linkTo(parent.start, 48.dp)
                        bottom.linkTo(parent.bottom)
                        verticalBias = 0.3f
                    }
                    .background(LemonGreen, shape = RoundedCornerShape(14.dp))
                    .padding(horizontal = 12.dp, vertical = 1.dp)
            )

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(icon) {
                        end.linkTo(parent.end, 52.dp)
                        bottom.linkTo(parent.bottom, 52.dp)
                    }
                    .padding(16.dp),
                tint = Color.White
            )
        }
    }
}

@Preview(fontScale = 1.0f, showBackground = true)
@Composable
fun PreviewBanner() {
    D4cAppTheme {
        Banner(banner = PromoBanner("GET 20% OFF", "GET 20% OFF", "12 - 16 October"))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPromoBanners() {
    D4cAppTheme {
        PromoBanners(
            modifier = Modifier.fillMaxWidth(),
            banners = listOf(
                PromoBanner("GET 10% OFF", "GET 20% OFF", "12 - 16 October"),
                PromoBanner("GET 20% OFF", "GET 30% OFF", "16 - 20 October"),
                PromoBanner("GET 30% OFF", "GET 40% OFF", "20 - 24 October"),
            )
        )
    }
}
