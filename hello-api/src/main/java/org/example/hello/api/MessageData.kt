package org.example.hello.api

import kotlinx.serialization.Serializable

@Serializable
data class MessageData (
    val message: String,
    val config: String,
    val hoconConfig: String
)
