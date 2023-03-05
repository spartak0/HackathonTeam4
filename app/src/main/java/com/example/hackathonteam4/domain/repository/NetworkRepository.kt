package com.example.hackathonteam4.domain.repository

import com.example.hackathonteam4.domain.model.Lamp
import com.example.hackathonteam4.domain.model.NetworkResult
import kotlinx.coroutines.flow.Flow


interface NetworkRepository {
    suspend fun getCurrentLamp(barcodeId: String): Flow<NetworkResult<Lamp>>
}