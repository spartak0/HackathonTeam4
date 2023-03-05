package com.example.hackathonteam4.data.network.lamps

import com.example.hackathonteam4.data.network.lamps.dto.GetLampResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface LampsApi {
    @GET("lamp/{barcode_id}")
    suspend fun getLamp(@Path("barcode_id") barcodeId: String) : GetLampResponseDto
}