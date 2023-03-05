package com.example.hackathonteam4.data.repository

import com.example.hackathonteam4.data.network.lamps.api.LampsApi
import com.example.hackathonteam4.data.network.lamps.dto.LampDto
import com.example.hackathonteam4.domain.model.Lamp
import com.example.hackathonteam4.domain.model.NetworkResult
import com.example.hackathonteam4.domain.repository.NetworkRepository
import com.example.hackathonteam4.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(
    private val api: LampsApi,
    private val lampMapper: Mapper<LampDto, Lamp>,
) : NetworkRepository {
    override suspend fun getCurrentLamp(barcodeId: String): Flow<NetworkResult<Lamp>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = api.getLamp(barcodeId)
            println(response)
            if (response.isSuccessful)
                response.body()?.let { lamp ->
                    emit(
                        NetworkResult.Success(
                            lampMapper.dtoToDomain(lamp)
                        )
                    )
                }
            else emit(NetworkResult.Error(response.message()))
        } catch (t: Throwable) {
            emit(NetworkResult.Error(t.message))
        }
    }
}