package com.example.offline.data
import androidx.annotation.DrawableRes

data class Look(
    val id: String,
    val name: String,
    val description: String,
    @DrawableRes
    val imageResId: Int,
    val silhouette: Int,
    val layering: Int,
    val palette: String,
    val hasStatementAccessories: Boolean
)