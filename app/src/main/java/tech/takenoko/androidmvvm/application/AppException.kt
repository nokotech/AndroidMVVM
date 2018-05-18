package tech.takenoko.androidmvvm.application

/**
 * Created by takenoko on 2018/02/17.
 */
class AppException(override val message: String, cause: Throwable) : Exception(message, cause)