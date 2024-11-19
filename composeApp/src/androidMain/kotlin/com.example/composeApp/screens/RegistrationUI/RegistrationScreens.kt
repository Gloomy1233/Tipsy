@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composeApp.screens
import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeApp.DataModels.User
import com.example.composeApp.screens.CommonsUI.Pager
import com.example.composeApp.screens.CommonsUI.PagerState
import com.example.composeApp.screens.RegistrationUI.Step1Screen
import com.example.composeApp.screens.RegistrationUI.Step2Screen
import com.example.composeApp.screens.RegistrationUI.Step3Screen
import com.example.composeApp.screens.RegistrationUI.Step4Screen
//import com.example.composeApp.screens.RegistrationUI.Step1Screen
//import com.example.composeApp.screens.RegistrationUI.Step2Screen
//import com.example.composeApp.screens.RegistrationUI.Step3Screen
//import com.example.composeApp.screens.RegistrationUI.Step4Screen
import com.example.composeApp.screens.ViewModels.RegistrationViewModel
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.ui.graphics.RectangleShape
import com.example.composeApp.screens.CommonsUI.BouncyHorizontalPager
import com.example.composeApp.screens.CommonsUI.CustomPagerIndicator
import com.example.composeApp.screens.CommonsUI.NavigationButtons
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.compose.ui.tooling.preview.Preview

import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp



@Composable
fun RegisterScreen(navController: NavController) {
    val registrationViewModel: RegistrationViewModel = viewModel()
    val pagerState = rememberPagerState(pageCount = { 4 }, initialPage = 0)
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.sdp), // Padding from the screen bottom
                contentAlignment = Alignment.Center // Centers everything inside the box
            ) {
                // Pager Indicator (Aligned to Center)
                CustomPagerIndicator(
                    totalPages = 4,
                    currentPage = pagerState.currentPage,
                )

                // Navigation Buttons (Positioned below the pager indicator)
                NavigationButtons(
                    currentPage = pagerState.currentPage,
                    totalPages = 4,
                    onBack = {
                        coroutineScope.launch {
                            if (pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    },
                    onNext = {
                        coroutineScope.launch {
                            if (pagerState.currentPage < 3) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            } else {
                                // Handle Finish action, e.g., save data
                                try {
                                    saveRegistrationDataToFirebase(registrationViewModel, navController)
                                } catch (e: Exception) {
                                    snackbarHostState.showSnackbar("Registration failed: ${e.message}")
                                }
                            }
                        }
                    },

                    isNextEnabled = isNextEnabled(registrationViewModel, pagerState.currentPage),
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 16.sdp),
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> Step1Screen(viewModel = registrationViewModel)
                        1 -> Step2Screen(viewModel = registrationViewModel)
                        2 -> Step3Screen(viewModel = registrationViewModel)
                        3 -> Step4Screen(viewModel = registrationViewModel)
                    }
                }


            }
        }
    )
}

@Composable
fun isNextEnabled(viewModel: RegistrationViewModel, currentPage: Int): Boolean {
    return when (currentPage) {
        0 -> viewModel.accountType
        1 -> viewModel.fullName.isNotBlank() &&
                viewModel.email.isNotBlank() &&
                viewModel.password == viewModel.repeatPassword &&
                viewModel.password.length >= 6 &&
                viewModel.phone.isNotBlank()
        2 -> viewModel.dateOfBirth.isNotBlank() // Adjust based on required fields
        3 -> true // On the last page, Finish is always enabled
        else -> false
    }
}
@Composable
fun LoadingIndicator(isLoading: Boolean) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}

suspend fun saveRegistrationDataToFirebase(viewModel: RegistrationViewModel, navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()

    try {
        // 1. Create user with email and password
        val authResult = auth.createUserWithEmailAndPassword(viewModel.email, viewModel.password).await()
        val uid = authResult.user?.uid ?: throw Exception("User UID is null")

        // 2. Upload profile picture if available
        var profilePictureUrl: String? = null
        if (!viewModel.profilePictureUri.isNullOrEmpty()) {
            val storageRef = storage.reference.child("profile_pictures/$uid.jpg")
            val uri = Uri.parse(viewModel.profilePictureUri)
            storageRef.putFile(uri).await()
            profilePictureUrl = storageRef.downloadUrl.await().toString()
        }

        // 3. Create User data object
        val user = User(
            uid = uid,
            accountType = viewModel.accountType,
            fullName = viewModel.fullName,
            email = viewModel.email,
            phone = viewModel.phone,
            isPhoneVisible = viewModel.isPhoneVisible,
            dateOfBirth = viewModel.dateOfBirth,
            sex = viewModel.sex,
            relationshipStatus = viewModel.relationshipStatus,
            bio = viewModel.bio,
            profilePictureUrl = profilePictureUrl
        )

        // 4. Save user data to Firestore
        firestore.collection("users").document(uid).set(user).await()

        Log.d("Registration", "User registered successfully with UID: $uid")

        // 5. Navigate back or to another screen
        navController.popBackStack()

    } catch (e: Exception) {
        // Handle exceptions (e.g., show error message to user)
        Log.e("Registration", "Error during registration: ${e.message}")
        // Optionally, show a Snackbar or dialog to inform the user
    }
}
