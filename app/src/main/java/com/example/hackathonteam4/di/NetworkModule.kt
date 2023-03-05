package com.example.hackathonteam4.di

import com.example.hackathonteam4.data.network.lamps.api.LampsApi
import com.example.hackathonteam4.data.repository.NetworkRepositoryImpl
import com.example.hackathonteam4.domain.mapper.LampMapper
import com.example.hackathonteam4.domain.repository.NetworkRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val BASE_URL = "http://event.surfstudio.ru:8084/"

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideApi(
        retrofit: Retrofit
    ): LampsApi = retrofit.create(LampsApi::class.java)

    @Provides
    @Singleton
    fun provideLampMapper(): LampMapper {
        return LampMapper()
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(lampsApi: LampsApi, mapper: LampMapper): NetworkRepository {
        return NetworkRepositoryImpl(lampsApi, mapper)
    }
}