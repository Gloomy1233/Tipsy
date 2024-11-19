package com.example.composeApp.screens.CommonsUI

// NavigationButtons.kt
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.automirrored.sharp.ArrowForward
import androidx.compose.material.icons.automirrored.sharp.FactCheck
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.composeApp.utility.primaryDark
import com.example.composeApp.utility.primaryOrange
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.ui.tooling.preview.Preview
@Composable
fun NavigationButtons(
    currentPage: Int,
    totalPages: Int,
    onBack: () -> Unit,
    onNext: () -> Unit,
    isNextEnabled: Boolean,
) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.sdp, vertical = 8.sdp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            // Back Button
            Button(
                onClick = onBack,
                enabled = currentPage > 0,

                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentPage > 0) primaryOrange else primaryDark,
                ),
                modifier = Modifier
                    .size(50.sdp) // Ensures circular shape
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.ArrowBack, // Use the desired Material Icon
                    contentDescription = "Back",
                    tint = Color.White // Icon color
                )
            }

            // Next Button
            Button(
                onClick = onNext,
                enabled = isNextEnabled,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNextEnabled) Color(0xFFE8827C) else Color.Gray
                ),
                modifier = Modifier
                    .size(50.sdp) // Ensures circular shape
            ) {
                Icon(
                    imageVector = if (currentPage < totalPages - 1) Icons.AutoMirrored.Sharp.ArrowForward  else Icons.AutoMirrored.Sharp.FactCheck, // Use the desired Material Icon
                    contentDescription = "Back",
                    tint = Color.White // Icon color
                )

            }
        }
    }


