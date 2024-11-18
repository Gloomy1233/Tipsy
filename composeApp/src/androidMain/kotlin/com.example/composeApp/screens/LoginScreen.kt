package com.example.composeApp.screens

import com.example.composeApp.utility.gradient
import com.example.composeApp.utility.primaryDark
import com.example.composeApp.utility.primaryOrange


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import com.example.R.drawable
import com.example.composeApp.utility.gradientOrange
import com.example.composeApp.utility.primaryDarkLighter
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons

@Composable
fun LoginScreen(navController: NavHostController) {
    SignInScreen(
        onLoginSuccess = {
            // Navigate to the home screen on login success
            navController.navigate("home")
        },
        onForgotPassword = {
            // Handle forgot password
        },
        onSignUp = {

            navController.navigate("register")
        }
    )

}

@Composable
fun SignInScreen(
    onLoginSuccess: () -> Unit,
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background shapes
        BackgroundShapes()

        // Scrollable Content
        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 16.sdp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.sdp) // Add spacing between items
        ) {
            // Logo
            item {
                Spacer(modifier = Modifier.height(24.sdp))
                LogoSection()
            }

            // Welcome Text
            item {
                Spacer(modifier = Modifier.height(24.sdp))
                WelcomeText()
            }

            // Email Input
            item {
                Spacer(modifier = Modifier.height(16.sdp))
                EmailInput()
            }

            // Password Input
            item {
                PasswordInput()
            }

            // Forgot Password Button
            item {
                ForgotPasswordButton(onForgotPassword)
            }

            // Login Button
            item {
                Spacer(modifier = Modifier.height(16.sdp))
                LoginButton(onLoginSuccess)
            }

            // Social Media Login
            item {
                SocialMediaLogin()
            }

            // Sign Up Section
            item {
                SignUpSection(onSignUp)
            }

            // Extra bottom padding
            item {
                Spacer(modifier = Modifier.height(24.sdp))
            }
        }
    }
        }



@Composable
fun SocialMediaLogin(){
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = 16.sdp)
    ) {

            Box(modifier = Modifier.padding(vertical = 16.sdp)) {
                Spacer(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(1.sdp)
                        .fillMaxWidth()
                        .background(primaryDark)
                )
                Text(
                    text = "Or use",
                    color = gradientOrange,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(primaryDark)
                        .padding(horizontal = 16.sdp)
                )
            }
        }

            OutlinedButton(
                onClick = { }, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(35.sdp)
            ) {
                FaIcon(
                    faIcon = FaIcons.Facebook,
                    tint = primaryDark,
                )
                Text(
                    text = "Sign in with Facebook",
                    style = TextStyle(fontSize = 14.ssp, color = primaryDarkLighter),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }


        Spacer(modifier = Modifier.height(8.sdp))


            OutlinedButton(
                onClick = { }, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(35.sdp)
            ) {
                FaIcon(
                    faIcon = FaIcons.Google,
                    tint = primaryDark,
                )
                Text(
                    text = "Sign in with Gmail",
                    style = TextStyle(fontSize = 14.ssp, color = primaryDarkLighter),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }




@Composable
fun LogoSection() {
    Card(
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(4.sdp),
        modifier = Modifier.size(200.sdp)
    ) {
        Image(
            painter = painterResource(id = drawable.logo ), // Replace with your logo resource
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun WelcomeText() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Let's Get The",
            style = TextStyle(fontSize = 36.sp, fontFamily = FontFamily.Serif),
            color = Color.Black
        )
        Row {
            Text(
                text = "Party ",
                style = TextStyle(fontSize = 36.sp, fontFamily = FontFamily.Serif),
                color = Color(0xFFE8827C)
            )
            Text(
                text = "Started!",
                style = TextStyle(fontSize = 36.sp, fontFamily = FontFamily.Serif),
                color = Color.Black
            )
        }
    }
}

@Composable
fun EmailInput() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) } // Track focus state

    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        trailingIcon = {
            Icon(
                imageVector = Icons.TwoTone.Email, // Clear icon
                contentDescription = "Email",
                tint =  primaryDark// Icon color
            )


        },
        label = {
            Text(
                text = "E-mail",
            )
        },
        textStyle = TextStyle(fontSize = 10.ssp),

        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = primaryDarkLighter,
            unfocusedTextColor = primaryDarkLighter,
            cursorColor = primaryOrange,
            focusedLabelColor = primaryOrange,
            unfocusedLabelColor = primaryOrange,
            unfocusedBorderColor = primaryDark,
            focusedBorderColor = primaryDark

        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.sdp)
            .height(45.sdp)

    )
}

@Composable
fun PasswordInput() {
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        trailingIcon = {
            val visibilityIcon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick =
            {
                passwordVisible = !passwordVisible
            }
            )
            {

                Icon(imageVector = visibilityIcon, contentDescription = description, tint = primaryDark)
            }
        },
        singleLine = true,
        textStyle = TextStyle(fontSize = 10.ssp),
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = primaryDarkLighter,
            unfocusedTextColor = primaryDarkLighter,
            cursorColor = primaryOrange,
            focusedLabelColor = primaryOrange,
            unfocusedLabelColor = primaryOrange,
            unfocusedBorderColor = primaryDark,
            focusedBorderColor = primaryDark

        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.sdp)
            .height(45.sdp)
    )
}

@Composable
fun ForgotPasswordButton(onForgotPassword: () -> Unit) {
    TextButton(onClick = onForgotPassword) {
        Text(
            text = "Forgot Password",
            color = primaryOrange,
            style = TextStyle(fontSize = 10.ssp)
        )
    }
}
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SignInScreen(
        onLoginSuccess = {
            // Navigate to the home screen on login success
        },
        onForgotPassword = {
            // Handle forgot password
        },
        onSignUp = {
            // Navigate to sign-up or another relevant screen
        })
}
@Composable
fun LoginButton(onLogin: () -> Unit) {
    Button(
        onClick = onLogin,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(40.sdp), // Adjust height to match design
        shape = RoundedCornerShape(28.sdp), // Fully rounded corners (half of height)
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF262838)), // Dark background
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.sdp) // Add elevation
    ) {
        Text(
            text = "LOG IN",
            style = TextStyle(
                brush = gradient,
                fontSize = 12.ssp, // Adjust text size to match design
                fontWeight = FontWeight.Bold, // Bold text for emphasis
                letterSpacing = 1.5.sp // Add letter spacing
            )
        )
    }
}
@Composable
fun SignUpSection(onSignUp: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Don't have an Account?",
            style = TextStyle(fontSize = 14.sp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(4.sdp))
        TextButton(onClick = onSignUp) {
            Text(
                text = "Sign Up",
                style = TextStyle(fontSize = 14.ssp, color = Color(0xFFE8827C))
            )
        }
    }
}

@Composable
fun BackgroundShapes() {
    val infiniteTransition = rememberInfiniteTransition()

    // Animation values for the first circle
    val scale1 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val rotation1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Animation values for the second circle
    val scale2 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val rotation2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Animation values for the third circle
    val scale3 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val rotation3 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Draw the animated balls (background shapes)
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // First circle
        Box(
            modifier = Modifier
                .size(150.sdp)
                .offset((-30).sdp, (-50).sdp)
                .graphicsLayer {
                    scaleX = scale1
                    scaleY = scale1
                    rotationZ = rotation1

                }
                .align(Alignment.TopStart)
                .background(Color(0xFFD7BBF5), CircleShape)
        )

        // Second circle
        Box(
            modifier = Modifier
                .size(250.sdp)
                .offset((120).sdp, (-70).sdp)
                .graphicsLayer {
                    scaleX = scale2
                    scaleY = scale2
                    rotationZ = rotation2

                }
                .align(Alignment.Center)
                .background(Color(0xFFA3DFF5), CircleShape)
        )

        // Third circle
        Box(
            modifier = Modifier
                .size(300.sdp)
                .offset((-50).sdp, (50).sdp)
                .graphicsLayer {
                    scaleX = scale3
                    scaleY = scale3
                    rotationZ = rotation3
                }
                .align(Alignment.BottomEnd)
                .background(Color(0xFFF5C8A3), CircleShape)
        )
    }

}

