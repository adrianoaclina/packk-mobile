package br.com.packkmobile.network.executor

import br.com.packkmobile.network.entity.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SafeRequest(
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun <T> invoke(lambda: suspend () -> T) = withContext(dispatcher) {
        try {
            Result.Success(lambda.invoke())
        } catch (throwable: Throwable) {
            NetworkErrorInterpreter.interpret(throwable)
        }
    }
}