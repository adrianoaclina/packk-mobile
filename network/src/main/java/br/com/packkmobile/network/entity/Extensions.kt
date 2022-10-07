package br.com.packkmobile.network.entity

inline fun <T> Result<T>.onError(lambda: (Result.Error) -> Unit): Result<T> {
    if (this is Result.Error) lambda.invoke(this)
    return this
}

inline fun <T> Result<T>.onSuccess(lambda: (T) -> Unit): Result<T> {
    if (this is Result.Success) lambda.invoke(data)
    return this
}