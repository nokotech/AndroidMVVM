package tech.takenoko.androidmvvm.utility

/**
 * Created by takenoko on 2018/02/09.
 */
abstract class UseCase<Q : UseCase.RequestValues, P : UseCase.ResponseValue> {

    private var requestValues: Q? = null
    private var useCaseCallback: UseCaseCallback<P>? = null

    fun setRequestValues(_requestValues: Q) {
        requestValues = _requestValues
    }

    fun getRequestValues(): Q? {
        return requestValues
    }

    fun getUseCaseCallback(): UseCaseCallback<P>? {
        return useCaseCallback
    }

    fun setUseCaseCallback(_useCaseCallback: UseCaseCallback<P>) {
        useCaseCallback = _useCaseCallback
    }

    internal fun run() {
        executeUseCase(requestValues)
    }

    protected abstract fun executeUseCase(requestValues: Q?)

    interface RequestValues

    interface ResponseValue

    interface UseCaseCallback<R> {
        fun onSuccess(response: R)
        fun onError()
    }
}