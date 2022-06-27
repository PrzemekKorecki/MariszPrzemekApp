package com.example.mariszprzemekapp

import java.util.*

data class MessageApp(
    val message: String = "",
    val senderName: String = "Przem_k",
    val date:String = Date().toString(),
    val zonk: String = Date().toString()
)