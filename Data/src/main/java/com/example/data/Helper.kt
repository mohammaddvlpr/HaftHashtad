package com.example.data

inline fun <T : Any> apiCall(call: () -> T): Result<T> {
    return try {
        Result.success(call())

    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure(e)
    }
}