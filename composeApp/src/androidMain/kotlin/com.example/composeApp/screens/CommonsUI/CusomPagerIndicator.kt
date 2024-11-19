package com.example.composeApp.screens.CommonsUI

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.composeApp.utility.primaryOrange
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun CustomPagerIndicator(
    totalPages: Int,
    currentPage: Int,
    activeColor: Color = primaryOrange,
    inactiveColor: Color = Color.Gray,
    indicatorSize: Dp = 15.sdp,
    spacing: Dp = 10.sdp
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 0 until totalPages) {
            Box(
                modifier = Modifier
                    .size(indicatorSize)
                    .align(Alignment.Bottom)
                    .padding(horizontal = spacing / 2, vertical = spacing / 2)
                    .background(
                        color = if (i <= currentPage) activeColor else inactiveColor,
                        shape = CircleShape
                    )
            )
        }
    }
}
