package com.example.jzunigamusicapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val albumId: String)
