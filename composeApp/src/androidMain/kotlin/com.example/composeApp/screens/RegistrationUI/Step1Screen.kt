package com.example.composeApp.screens.RegistrationUI

// Step1Screen.kt
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.R
import com.example.composeApp.screens.ViewModels.RegistrationViewModel
import com.example.composeApp.utility.gradient
import com.example.composeApp.utility.primaryDark
import com.example.composeApp.utility.primaryOrange
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun Step1Screen(viewModel: RegistrationViewModel) {
    var visible by remember { mutableStateOf(false) }
    var isHost by remember { mutableStateOf(false) }
    var isUser by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }


    Column(
        modifier = Modifier
            .fillMaxSize().padding(16.sdp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(onClick = {
            isUser = true
            isHost = false
        },
            shape = RoundedCornerShape(16.sdp),
            colors = CardDefaults.cardColors(
                containerColor = if(isUser)   primaryDark else primaryOrange,
                ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.sdp,
                pressedElevation = 4.sdp,
                focusedElevation = 4.sdp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.sdp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.sdp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Profile Image
//            Image(
//                painter = painterResource(id = imageRes),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(60.sdp)
//                    .clip(CircleShape),
//                contentScale = ContentScale.Crop
//            )

                Spacer(modifier = Modifier.width(16.sdp))

                // Text Section
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "title",
                        style = TextStyle(
                            fontSize = 18.ssp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFC4C4)
                        )
                    )
                    Text(
                        text = "subtitle",
                        style = TextStyle(
                            fontSize = 14.ssp,
                            color = Color(0xFFFFC4C4)
                        )
                    )
                }

                Spacer(modifier = Modifier.width(16.sdp))

                // Selection Icon
                if (isUser) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Selected",
                        tint = Color(0xFFFFC4C4),
                        modifier = Modifier.size(24.sdp)
                    )
                }
            }
        }

        Card(onClick = {
            isUser = false
            isHost = true
        },
            shape = RoundedCornerShape(16.sdp),
            colors = CardDefaults.cardColors(
                containerColor = if(isHost) primaryDark else primaryOrange,
                disabledContainerColor = primaryDark),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.sdp,
                pressedElevation = 4.sdp,
                focusedElevation = 4.sdp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.sdp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.sdp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Profile Image
//            Image(
//                painter = painterResource(id = imageRes),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(60.sdp)
//                    .clip(CircleShape),
//                contentScale = ContentScale.Crop
//            )

                Spacer(modifier = Modifier.width(16.sdp))

                // Text Section
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "title",
                        style = TextStyle(
                            fontSize = 18.ssp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFC4C4)
                        )
                    )
                    Text(
                        text = "subtitle",
                        style = TextStyle(
                            fontSize = 14.ssp,
                            color = Color(0xFFFFC4C4)
                        )
                    )
                }

                Spacer(modifier = Modifier.width(16.sdp))

                // Selection Icon
                if (isHost) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Selected",
                        tint = Color(0xFFFFC4C4),
                        modifier = Modifier.size(24.sdp)
                    )
                }
            }
        }
        PartyOptionCard(
            title = "Party",
            subtitle = "I just want to Party",
            imageRes = R.drawable.logo,
            isHost = viewModel.accountType,
            onCardClick = {
                viewModel.accountType = true
            }
        )

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(initialAlpha = 0.3f),
            exit = fadeOut()
        ) {
            Text(
                text = "What account type do you want?\n(You can change this later)",
                textAlign = TextAlign.Center,
                fontSize = 18.ssp
            )
        }

        Spacer(modifier = Modifier.height(16.sdp))

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Button(
                onClick = {
                    viewModel.accountType = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8827C)),
                modifier = Modifier
                    .animateContentSize()
                    .fillMaxWidth(0.7f)
                    .padding(bottom = 32.sdp)
                    .height(50.sdp)
                    .clip(CircleShape)
            ) {
                Text(text = "I just want to Party")
            }
        }

        Spacer(modifier = Modifier.height(8.sdp))

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Button(
                onClick = {
                    viewModel.accountType = false
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8827C)),
                modifier = Modifier
                    .animateContentSize()
                    .fillMaxWidth(0.7f)
                    .padding(bottom = 32.sdp)
                    .height(50.sdp)
                    .clip(CircleShape)
            ) {
                Text(text = "I want to host Events")
            }
        }

        Spacer(modifier = Modifier.height(16.sdp))

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            TextButton(onClick = { /* Implement navigation to Sign In */ }) {
                Text(text = "Already have an account? Sign in", color = Color(0xFFE8827C))
            }
        }
    }
}

@Composable
fun PartyOptionCard(
    title: String,
    subtitle: String,
    imageRes: Int, // Resource ID for the image
    isHost: Boolean,
    onCardClick: () -> Unit
) {

}