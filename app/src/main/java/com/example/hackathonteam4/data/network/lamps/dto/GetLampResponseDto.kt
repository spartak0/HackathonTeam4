package com.example.hackathonteam4.data.network.lamps.dto

data class GetLampResponseDto(
    val brand: String = "",
    val model: String = "",
    val lampDescription: String = "",
    val priceInRub: Double = 0.0,
    val priceInUsd: Double = 0.0,
    val power: Double = 0.0,
    val lm: Int = 0,
    val efficiency: Double = 0.0,
    val equivalent: Int = 0,
    val colorTemp: Int = 0,
    val cri: Double = 0.0,
    val angle: Int = 0,
    val flickerFactor: Int = 0,
    val workWithBacklightIndicator: String = "",
    val rating: Double = 0.0,
    val warranty: Int = 0,
    val actuality: Boolean = false,
)
