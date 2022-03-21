package com.example.dz_sofascore.model

import java.io.Serializable

data class F1Driver constructor(
    val firstName: String,
    val lastName: String,
    val team: String,
    val nationality: String,
    val favoriteTrack: String,
    val teammate: String,
    val engineConstructor: String
) : Serializable
