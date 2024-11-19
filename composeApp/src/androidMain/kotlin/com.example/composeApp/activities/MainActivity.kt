package com.example.composeApp.activities


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeApp.commons.MyApplicationTheme
import com.example.composeApp.screens.LoginScreen
import com.example.composeApp.screens.RegisterScreen
import com.example.composeApp.utility.gradientPink
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirebaseApp.initializeApp(this)

                    MainContent()

                }
            }


        }
    }


    @Composable
    fun AppNavGraph() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "login") {
            composable("login") { LoginScreen(navController) }
            composable("register") { RegisterScreen(
                navController,
            ) } // Register screen
        }
    }
    @Preview(showBackground = true)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainContent() {
        val coroutineScope = rememberCoroutineScope()
        MaterialTheme {
            Scaffold(

            ) { padding ->
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AppNavGraph()

                    Button(onClick = {
                        showProgressDialog()
                        coroutineScope.launch {
                            delay(5000)
                            hideProgressDialog()
                            showToast("Task Completed!")
                        }
                    }) {
                        Text("Show Progress Dialog")
                    }
                    PartyApp()
                }
            }


        }
    }
    @Composable
    fun PartyApp() {
        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize()) {

                PartyScreen()

            }
        }
    }

    @Composable
    fun PartyScreen() {
        val partyList = remember { mutableStateListOf<String>() }
        var partyName by remember { mutableStateOf(TextFieldValue("")) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Party Creation App",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            BasicTextField(
                value = partyName,
                onValueChange = { partyName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(56.dp),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    ) {
                        if (partyName.text.isEmpty()) {
                            Text(
                                text = "Enter party name",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Button(
                onClick = {
                    if (partyName.text.isNotBlank()) {
                        partyList.add(partyName.text)
                        partyName = TextFieldValue("")
                    }
                },
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Add Party")
            }
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Password*") },
                visualTransformation = PasswordVisualTransformation(),
                colors = androidx.compose.material3.TextFieldDefaults.colors(
                    cursorColor = gradientPink,

                ),
                modifier = Modifier.fillMaxWidth()
            )

            PartyList(partyList)
        }
    }

    @Composable
    fun PartyList(partyList: SnapshotStateList<String>) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(partyList) { party ->
                PartyItem(partyName = party)
            }
        }
    }

    @Composable
    fun PartyItem(partyName: String) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = partyName,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
    @Preview
    @Composable
    fun DefaultPreview() {
        PartyApp()
    }
}




