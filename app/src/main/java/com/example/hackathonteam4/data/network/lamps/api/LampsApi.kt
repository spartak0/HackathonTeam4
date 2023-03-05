package com.example.hackathonteam4.data.network.lamps.api

import com.example.hackathonteam4.data.network.lamps.dto.LampDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LampsApi {
    @GET("api/v1/lamp/{barcode_id}")
    suspend fun getLamp(@Path("barcode_id") barcodeId: String) : Response<LampDto>
}