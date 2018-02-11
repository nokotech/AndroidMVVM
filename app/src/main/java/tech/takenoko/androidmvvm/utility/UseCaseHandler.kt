package tech.takenoko.androidmvvm.utility

/**
 * Created by takenoko on 2018/02/09.
 */
class UseCaseHandler(private val mUseCaseScheduler: UseCaseScheduler) {

    companion object {
        private var inst: UseCaseHandler? = null
        val instance: UseCaseHandler?
            get() {
                if (inst == null) inst = UseCaseHandler(UseCaseThreadPoolScheduler())
                return inst
            }
    }

    fun <T : UseCase.RequestValues, R : UseCase.ResponseValue> execute(useCase: UseCase<T, R>, values: T, callback: UseCase.UseCaseCallback<R>) {
        useCase.setRequestValues(values)
        useCase.setUseCaseCallback(UiCallbackWrapper(callback, this))
        mUseCaseScheduler.execute(Runnable {
            useCase.run()
        })
    }

    fun <V : UseCase.ResponseValue> notifyResponse(response: V, useCaseCallback: UseCase.UseCaseCallback<V>) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback)
    }

    private fun <V : UseCase.ResponseValue> notifyError(useCaseCallback: UseCase.UseCaseCallback<V>) {
        mUseCaseScheduler.onError(useCaseCallback)
    }

    private class UiCallbackWrapper<V : UseCase.ResponseValue>(private val mCallback: UseCase.UseCaseCallback<V>, private val mUseCaseHandler: UseCaseHandler) : UseCase.UseCaseCallback<V> {
        override fun onSuccess(response: V) {
            mUseCaseHandler.notifyResponse(response, mCallback)
        }

        override fun onError() {
            mUseCaseHandler.notifyError(mCallback)
        }
    }
}