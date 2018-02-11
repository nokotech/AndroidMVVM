package tech.takenoko.androidmvvm.utility

import android.os.Handler
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by takenoko on 2018/02/09.
 */
abstract class StubUseCase<Q: StubUseCase.RequestValues, P: StubUseCase.ResponseValue> {

    interface RequestValues
    interface ResponseValue
    interface UseCaseCallback<R> {
        fun onSuccess(response: R)
        fun onError()
    }
    interface UseCaseScheduler {
        fun execute(runnable: Runnable)
        fun <V: StubUseCase.ResponseValue> notifyResponse(response: V, stubUseCaseCallback: StubUseCase.UseCaseCallback<V>)
        fun <V: StubUseCase.ResponseValue> onError(stubUseCaseCallback: StubUseCase.UseCaseCallback<V>)
    }

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

    class UseCaseHandler(private val mStubUseCaseScheduler: StubUseCase.UseCaseScheduler) {

        companion object {
            private var inst: UseCaseHandler? = null
            val instance: UseCaseHandler?
                get() {
                    inst = inst?: UseCaseHandler(UseCaseThreadPoolScheduler())
                    return inst
                }
        }

        fun <T: StubUseCase.RequestValues, R: StubUseCase.ResponseValue> execute(stubUseCase: StubUseCase<T, R>, values: T, callback: StubUseCase.UseCaseCallback<R>) {
            stubUseCase.setRequestValues(values)
            stubUseCase.setUseCaseCallback(UiCallbackWrapper(callback, this))
            mStubUseCaseScheduler.execute(Runnable {
                stubUseCase.run()
            })
        }
        fun <V: StubUseCase.ResponseValue> notifyResponse(response: V, stubUseCaseCallback: StubUseCase.UseCaseCallback<V>) {
            mStubUseCaseScheduler.notifyResponse(response, stubUseCaseCallback)
        }

        private fun <V: StubUseCase.ResponseValue> notifyError(stubUseCaseCallback: StubUseCase.UseCaseCallback<V>) {
            mStubUseCaseScheduler.onError(stubUseCaseCallback)
        }
        private class UiCallbackWrapper<V: StubUseCase.ResponseValue>(private val mCallback: StubUseCase.UseCaseCallback<V>, private val mUseCaseHandler: UseCaseHandler): StubUseCase.UseCaseCallback<V> {
            override fun onSuccess(response: V) {
                mUseCaseHandler.notifyResponse(response, mCallback)
            }

            override fun onError() {
                mUseCaseHandler.notifyError(mCallback)
            }
        }

        class UseCaseThreadPoolScheduler: StubUseCase.UseCaseScheduler {
            private val mHandler = Handler()
            val POOL_SIZE = 2
            val MAX_POOL_SIZE = 4
            val TIMEOUT = 30L

            internal var mThreadPoolExecutor: ThreadPoolExecutor

            init {
                mThreadPoolExecutor = ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, TimeUnit.SECONDS, ArrayBlockingQueue<Runnable>(POOL_SIZE))
            }

            override fun execute(runnable: Runnable) {
                mThreadPoolExecutor.execute(runnable)
            }

            override fun <V: StubUseCase.ResponseValue> notifyResponse(response: V, stubUseCaseCallback: StubUseCase.UseCaseCallback<V>) {
                mHandler.post(Runnable { stubUseCaseCallback.onSuccess(response) })
            }

            override fun <V: StubUseCase.ResponseValue> onError(stubUseCaseCallback: StubUseCase.UseCaseCallback<V>) {
                mHandler.post(Runnable { stubUseCaseCallback.onError() })
            }
        }
    }
}