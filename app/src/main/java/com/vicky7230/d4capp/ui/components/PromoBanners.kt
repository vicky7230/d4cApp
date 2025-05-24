package com.vicky7230.d4capp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
    val pagerState = rememberPagerState(pageCount = { banners.size })
    val pagerIsDragged by pagerState.interactionSource.collectIsDraggedAsState()

    val pageInteractionSource = remember { MutableInteractionSource() }
    val pageIsPressed by pageInteractionSource.collectIsPressedAsState()

    // Stop auto-advancing when pager is dragged or one of the pages is pressed
    val autoAdvance = !pagerIsDragged && !pageIsPressed

    if (autoAdvance) {
        LaunchedEffect(pagerState, pageInteractionSource) {
            while (true) {
                delay(2000)
                val nextPage = (pagerState.currentPage + 1) % banners.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Box(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->

            Banner(banner = banners[page])
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
                            color = if (index == pagerState.currentPage) LemonGreen else Color.Black
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
    Box(
        modifier = modifier
            .fillMaxWidth()
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
