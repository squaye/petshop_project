package com.samq.petshop.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.Date

class Order(
    val initialName: String = "",
    val initialEmail: String = "",
    val initialPhone: String = "",
    val initialCardNumber: String = "",
){
    var id: Int = 0
    var totalPrice: Double = 0.0
    var totalItems: Double = 0.0
    var name by mutableStateOf(initialName)
    var email by mutableStateOf(initialEmail)
    var phone by mutableStateOf(initialPhone)
    var cardNumber by mutableStateOf(initialCardNumber)
    var date: Date = Date()
    var status: String ="Pending"
}