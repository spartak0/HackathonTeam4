package com.example.hackathonteam4.data.repository

import com.example.hackathonteam4.data.network.lamps.LampsApi
import com.example.hackathonteam4.data.network.lamps.dto.GetLampResponseDto
import com.example.hackathonteam4.domain.model.Lamp
import com.example.hackathonteam4.domain.repository.LampRepository
import com.example.hackathonteam4.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LampNetworkRepository @Inject constructor(
    private val api: LampsApi,
    private val lampMapper: Mapper<GetLampResponseDto, Lamp>,
): LampRepository {
    override suspend fun getCurrentLamp(barcodeId: String): Flow<Lamp> = flow {
        val lamp = lampMapper.dtoToDomain(api.getLamp(barcodeId))
        emit(lamp)
    }
}