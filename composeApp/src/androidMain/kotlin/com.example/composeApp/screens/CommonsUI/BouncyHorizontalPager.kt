package com.example.composeApp.screens.CommonsUI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

@Composable
fun BouncyHorizontalPager(
    pagerState: PagerState,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    content: @Composable (page: Int) -> Unit
) {
    HorizontalPager(
        state = pagerState,
        contentPadding = contentPadding,
        modifier = modifier
    ) { page ->
        val pageOffset = pagerState.currentPageOffsetFraction

        // Apply bounce transformation
        val scale = 1f - 0.1f * pageOffset.coerceAtMost(1f)
        var translationY = 50f * pageOffset.coerceAtMost(1f)

        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationY = translationY
                }
        ) {
            content(page)
        }
    }
}