package com.example.composeApp.DataModels

data class User(
    val uid: String,
    val accountType: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val isPhoneVisible: Boolean,
    val dateOfBirth: String,
    val sex: String,
    val relationshipStatus: String,
    val bio: String,
    val profilePictureUrl: String? = null
)