package com.android.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

abstract class SingleUseCase<Type, in Params> where Type : Any? {

    abstract suspend fun run(params: Params? = null): Type?

    fun invoke(scope: CoroutineScope, params: Params?, onResult: UseCaseResponse<Type>?) {
        scope.launch {
            try {
                val result = run(params)
                onResult?.onSuccess(result)
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResult?.onError(e)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult?.onError(e)
            }
        }
    }
}