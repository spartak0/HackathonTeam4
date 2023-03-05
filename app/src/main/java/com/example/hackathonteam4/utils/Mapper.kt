package com.example.hackathonteam4.utils

interface Mapper<Dto, Domain> {

    fun domainToDto(domain: Domain) : Dto

    fun dtoToDomain(dto: Dto) : Domain
}