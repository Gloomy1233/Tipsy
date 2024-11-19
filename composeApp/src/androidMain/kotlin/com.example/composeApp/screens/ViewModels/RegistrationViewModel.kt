package com.example.composeApp.screens.ViewModels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class RegistrationViewModel : ViewModel() {
    // Step 1: Account Type
    var accountType by mutableStateOf(false)

    // Step 2: Required Info
    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var repeatPassword by mutableStateOf("")
    var phone by mutableStateOf("")
    var isPhoneVisible by mutableStateOf(false)

    // Step 3: Optional Info
    var dateOfBirth by mutableStateOf("")
    var sex by mutableStateOf("")
    var relationshipStatus by mutableStateOf("")
    var bio by mutableStateOf("")

    // Step 4: Profile Picture
    var profilePictureUri by mutableStateOf<String?>(null)

    // Add validation logic as needed
    override fun toString(): String {
        return "accountType='$accountType', fullName='$fullName', email='$email', password='${password.take(10)}', repeatPassword='${repeatPassword.take(10)}', phone='$phone', isPhoneVisible=$isPhoneVisible, dateOfBirth='$dateOfBirth', sex='$sex', relationshipStatus='$relationshipStatus', bio='$bio', profilePictureUri='$profilePictureUri')"
    }
}
