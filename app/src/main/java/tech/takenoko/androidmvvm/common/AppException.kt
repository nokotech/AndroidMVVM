package tech.takenoko.androidmvvm.common

/**
 * Created by takenoko on 2018/02/17.
 */
class AppException(override val message: String, cause: Throwable) : Exception(message, cause)