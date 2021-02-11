package com.windranger.domain.usecases

interface BaseUseCase<in PARAMS, OUTPUT> {
    suspend fun execute(params: PARAMS?): OUTPUT
}