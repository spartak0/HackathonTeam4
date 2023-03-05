package com.example.hackathonteam4.domain.mapper

import com.example.hackathonteam4.data.network.lamps.dto.GetLampResponseDto
import com.example.hackathonteam4.domain.model.Lamp
import com.example.hackathonteam4.utils.Mapper

class LampMapper : Mapper<GetLampResponseDto, Lamp> {
    override fun domainToDto(domain: Lamp): GetLampResponseDto = with(domain) {
        return GetLampResponseDto(
            brand,
            model,
            lampDescription,
            priceInRub,
            priceInUsd,
            power,
            lm,
            efficiency,
            equivalent,
            colorTemp,
            cri,
            angle,
            flickerFactor,
            workWithBacklightIndicator,
            rating,
            warranty,
            actuality)
    }

    override fun dtoToDomain(dto: GetLampResponseDto): Lamp = with(dto) {
        return Lamp(
            brand ?: "",
            model ?: "",
            lampDescription ?: ":",
            priceInRub ?: 0.0,
            priceInUsd ?: 0.0,
            power ?: 0.0,
            lm ?: 0,
            efficiency ?: 0.0,
            equivalent ?: 0,
            colorTemp ?: 0,
            cri ?: 0.0,
            angle ?: 0,
            flickerFactor ?: 0,
            workWithBacklightIndicator ?: "",
            rating ?: 0.0,
            warranty ?: 0,
            actuality ?: false)
    }
}