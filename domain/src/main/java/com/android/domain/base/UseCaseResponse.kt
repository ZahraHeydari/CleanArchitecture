package com.android.domain.base

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type?)

    fun onError(throwable: Throwable)
}