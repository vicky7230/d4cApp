package com.vicky7230.d4capp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicky7230.d4capp.ui.theme.D4cAppTheme
import com.vicky7230.d4capp.ui.theme.LemonGreen
import com.vicky7230.d4capp.ui.theme.LightBlack

@Composable
fun ActionItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    count: Int,
    contentDescription: String
) {
    Box(
        modifier = modifier
            .size(30.dp)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.TopStart),
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = Color.White
        )
        if (count > 0)
            CountIndicator(
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.BottomEnd),
                count = count
            )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActionItem() {
    D4cAppTheme {
        Box(Modifier.background(color = LightBlack)) {
            ActionItem(
                imageVector = Icons.Default.FavoriteBorder,
                count = 5,
                contentDescription = "content"
            )
        }
    }
}

@Composable
fun CountIndicator(
    modifier: Modifier = Modifier,
    count: Int
) {
    Box(
        modifier = modifier
            .background(
                LemonGreen,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = count.toString(),
            color = LightBlack,
            fontSize = 12.sp,
            style = TextStyle(
                lineHeight = 12.sp,
                fontWeight = FontWeight.Medium,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCountIndicator() {
    D4cAppTheme {
        Box(Modifier.background(color = LightBlack)) {
            CountIndicator(modifier = Modifier.size(18.dp), count = 5)
        }
    }
}