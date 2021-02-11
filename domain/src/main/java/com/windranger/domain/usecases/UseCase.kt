package com.windranger.domain.usecases

interface UseCase<in PARAMS, OUTPUT> {
    fun execute(params: PARAMS?): OUTPUT
}