package org.example.hello.api

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

object KSerializerSettings {

    @OptIn(ExperimentalSerializationApi::class)
    val json = Json { namingStrategy = JsonNamingStrategy.SnakeCase }
}