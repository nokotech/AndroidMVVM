package tech.takenoko.androidmvvm.utility

import android.os.Handler
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
 * Created by takenoko on 2018/02/09.
 */
class UseCaseThreadPoolScheduler : UseCaseScheduler {

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

    override fun <V : UseCase.ResponseValue> notifyResponse(response: V, useCaseCallback: UseCase.UseCaseCallback<V>) {
        mHandler.post(Runnable { useCaseCallback.onSuccess(response) })
    }

    override fun <V : UseCase.ResponseValue> onError(useCaseCallback: UseCase.UseCaseCallback<V>) {
        mHandler.post(Runnable { useCaseCallback.onError() })
    }
}