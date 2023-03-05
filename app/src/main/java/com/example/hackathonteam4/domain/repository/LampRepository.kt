package com.example.hackathonteam4.domain.repository

import com.example.hackathonteam4.domain.model.Lamp
import kotlinx.coroutines.flow.Flow


interface LampRepository {
    suspend fun getCurrentLamp() : Flow<Lamp>
}