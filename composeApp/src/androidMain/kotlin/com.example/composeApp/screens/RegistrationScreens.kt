@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composeApp.screens
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeApp.DataModels.User
import com.example.composeApp.screens.CommonsUI.Pager
import com.example.composeApp.screens.CommonsUI.PagerState
import com.example.composeApp.screens.ViewModels.RegistrationViewModel
import com.google.firebase.FirebaseApp

import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp



@Composable
fun RegisterScreen(navController: NavController,context: Context) {
    FirebaseApp.initializeApp(context)
    val registrationViewModel: RegistrationViewModel = viewModel()


    val pagerState = remember { PagerState(currentPage = 0, minPage = 0, maxPage = 3) }
    val steps = listOf<@Composable () -> Unit>(
        {
            Step1Screen(registrationViewModel,onNext = { pagerState.currentPage += 1 })
        },
        {
            Step2Screen(
                registrationViewModel,
                onNext = { pagerState.currentPage += 1 },
                onBack = { pagerState.currentPage -= 1 }
            )
        },
        {
            Step3Screen(
                registrationViewModel,
                onNext = { pagerState.currentPage += 1 },
                onBack = { pagerState.currentPage -= 1 }
            )
        },
        {
            Step4Screen(
                registrationViewModel,
                onFinish = {
                    // Complete registration and navigate back
                    navController.popBackStack()
                },
                onBack = { pagerState.currentPage -= 1 }
            )
        }
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Pager(
            state = pagerState,
            orientation = Orientation.Horizontal,
            modifier = Modifier.fillMaxSize()
        ) {
            steps[commingPage]()
        }
    }
}



@Composable
fun Step1Screen(viewModel: RegistrationViewModel,onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "What account type do you want?\n(You can change this later)",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.sdp))

        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8827C)),
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth(0.7f)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.sdp)
                .height(50.sdp)
                .clip(CircleShape),
        ) {
            Text(text = "I just want to Party")
        }

        Spacer(modifier = Modifier.height(8.sdp))

        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8827C)),
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth(0.7f)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.sdp)
                .height(50.sdp)
                .clip(CircleShape),
        ) {
            Text(text = "I want to host Events")
        }

        Spacer(modifier = Modifier.height(16.sdp))

        TextButton(onClick = { /* Sign in functionality */ }) {
            Text(text = "Already have an account? Sign in", color = Color(0xFFE8827C))
        }
    }
}
@Composable
fun Step2Screen(viewModel: RegistrationViewModel,onNext: () -> Unit, onBack: () -> Unit) {
    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Some important info to complete your registration", fontSize = 10.ssp)

        TextField(value = "", onValueChange = {}, label = { Text("Full Name*") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email,
            onValueChange =
                    {email=it;
                        viewModel.email=email},
            label = { Text("E-mail*") },

             modifier = Modifier.fillMaxWidth().height(70.sdp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password=it;
                viewModel.password=password},
            label = { Text("Password*") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().height(70.sdp)
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Repeat Password*") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(value = "", onValueChange = {}, label = { Text("Phone*") }, modifier = Modifier.fillMaxWidth())

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Do you want the number to be visible?" +
                    "\n(You can change this later)",
                style = TextStyle(fontSize = 10.ssp)
            )
            Switch(checked = false, onCheckedChange = {})
        }

        Row(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onBack,modifier = Modifier
                .animateContentSize()
                .padding(bottom = 10.sdp)
                .height(35.sdp)
                .width(75.sdp)
                .clip(CircleShape),) {
                Text("Back")
            }
            Button(onClick = onNext,modifier = Modifier
                .animateContentSize()
                .padding(bottom = 10.sdp)
                .width(75.sdp)
                .height(35.sdp)
                .clip(CircleShape),) {
                Text("Next")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Step3Screen(viewModel: RegistrationViewModel,onNext: () -> Unit, onBack: () -> Unit) {
    // State for Sex dropdown
    var selectedSex by remember { mutableStateOf("") }
    var sexExpanded by remember { mutableStateOf(false) }
    val sexOptions = listOf("Male", "Female", "Other")

    // State for Relationship Status dropdown
    var selectedStatus by remember { mutableStateOf("") }
    var statusExpanded by remember { mutableStateOf(false) }
    val statusOptions = listOf("Single", "In a relationship", "Married", "It's complicated")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "You can leave this blank and edit it later", fontSize = 18.ssp)

        // Date of Birth TextField (you might want to use a date picker here)
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Date of Birth") },
            modifier = Modifier.fillMaxWidth()
        )

        // Sex Dropdown
        ExposedDropdownMenuBox(
            expanded = sexExpanded,
            onExpandedChange = { sexExpanded = !sexExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selectedSex,
                onValueChange = {},
                readOnly = true,
                label = { Text("Sex") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = sexExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = sexExpanded,
                onDismissRequest = { sexExpanded = false }
            ) {
                sexOptions.forEach { sex ->
                    DropdownMenuItem(
                        text = { Text(text = sex) },
                        onClick = {
                            selectedSex = sex
                            sexExpanded = false
                        }
                    )
                }
            }
        }

        // Relationship Status Dropdown
        ExposedDropdownMenuBox(
            expanded = statusExpanded,
            onExpandedChange = { statusExpanded = !statusExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selectedStatus,
                onValueChange = {viewModel.email="svdsvdssd";viewModel.password="sacascsacsa";
                    Log.d("daadsasd","asdasdsadas3"+viewModel.email+viewModel.password)
                },
                readOnly = true,
                label = { Text("Relationship Status") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = statusExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = statusExpanded,
                onDismissRequest = { statusExpanded = false }
            ) {
                statusOptions.forEach { status ->
                    DropdownMenuItem(
                        text = { Text(text = status) },
                        onClick = {
                            selectedStatus = status
                            statusExpanded = false
                        }
                    )
                }
            }
        }

        // Bio TextField
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Bio") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.sdp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onBack,modifier = Modifier
                .animateContentSize()
                .padding(bottom = 10.sdp)
                .height(35.sdp)
                .width(75.sdp)
                .clip(CircleShape),) {
                Text("Back")
            }
            Button(onClick = onNext,modifier = Modifier
                .animateContentSize()
                .padding(bottom = 10.sdp)
                .width(75.sdp)
                .height(35.sdp)
                .clip(CircleShape),) {
                Text("Next")
            }
        }
    }
}

@Composable
fun Step4Screen(viewModel: RegistrationViewModel,onFinish: () -> Unit, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Let's add a profile picture and you're ready to go!\n(You can change this later)",
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .size(200.sdp)
                .background(Color.LightGray, CircleShape)
        ) {
            // Add profile picture functionality here
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { /* Camera functionality */ }) {
                Icon(Icons.Default.Camera, contentDescription = "Camera")
            }
            IconButton(onClick = { /* Upload functionality */ }) {
                Icon(Icons.Default.Upload, contentDescription = "Upload")
            }
        }
        Log.d("daadsasd","asdasdsadas2"+viewModel.email+viewModel.password)

        Row(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onBack,modifier = Modifier
                .animateContentSize()
                .padding(bottom = 10.sdp)
                .height(35.sdp)
                .width(75.sdp)
                .clip(CircleShape),) {
                if(viewModel.email.isEmpty()||viewModel.password.isEmpty())
                    return@Button
                Log.d("daadsasd","asdasdsadas1"+viewModel.email+viewModel.password)

                saveRegistrationDataToFirebase(viewModel)
                Text("Back")
            }
            Button(onClick = onFinish,modifier = Modifier
                .animateContentSize()
                .padding(bottom = 10.sdp)
                .width(75.sdp)
                .height(35.sdp)
                .clip(CircleShape),) {
                Text("Next")
            }

        }
    }
}
// If using Firestore:
// import com.google.firebase.firestore.FirebaseFirestore

fun saveRegistrationDataToFirebase(viewModel: RegistrationViewModel) {
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("users")
    // For Firestore:
    // val db = FirebaseFirestore.getInstance()
    // val usersRef = db.collection("users")
    Log.d("daadsasd","asdasdsadas"+viewModel.email+viewModel.password)
    // Create a new user with email and password
    auth.createUserWithEmailAndPassword(viewModel.email, viewModel.password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Registration successful
                val uid = auth.currentUser?.uid ?: ""
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
                    profilePictureUrl = viewModel.profilePictureUri // You need to upload the picture separately
                )

                // Save user data to Realtime Database
                usersRef.child(uid).setValue(user)
                    .addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            // Data saved successfully
                        } else {
                            // Handle failure
                        }
                    }
                // For Firestore:
                // usersRef.document(uid).set(user)
                //     .addOnSuccessListener { /* ... */ }
                //     .addOnFailureListener { /* ... */ }
            } else {
                // Handle authentication failure
            }
        }
}
