package com.example.hackathonteam4.di

import com.example.hackathonteam4.data.network.lamps.dto.GetLampResponseDto
import com.example.hackathonteam4.data.repository.LampNetworkRepository
import com.example.hackathonteam4.domain.mapper.LampMapper
import com.example.hackathonteam4.domain.model.Lamp
import com.example.hackathonteam4.domain.repository.LampRepository
import com.example.hackathonteam4.utils.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindMapper(
        lampMapper: LampMapper
    ) : Mapper<GetLampResponseDto, Lamp>

    @Binds
    abstract fun bindLampRepository(
        lampNetworkRepository: LampNetworkRepository
    ) : LampRepository
}