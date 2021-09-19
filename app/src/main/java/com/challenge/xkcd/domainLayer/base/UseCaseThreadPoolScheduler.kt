package com.challenge.xkcd.domainLayer.base

import android.os.Handler
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UseCaseThreadPoolScheduler @Inject constructor() : UseCaseScheduler {

    val POOL_SIZE = 2

    val MAX_POOL_SIZE = 4

    val TIMEOUT = 30

    private val mHandler = Handler()

    private var mThreadPoolExecutor: ThreadPoolExecutor

    init {
        mThreadPoolExecutor = ThreadPoolExecutor(
            POOL_SIZE, MAX_POOL_SIZE, TIMEOUT.toLong(),
            TimeUnit.SECONDS, ArrayBlockingQueue(POOL_SIZE)
        )
    }

    override fun execute(runnable: Runnable) {
        mThreadPoolExecutor.execute(runnable)
    }

    override fun <V : UseCase.ResponseValue> notifyResponse(
        response: V,
        useCaseCallback: UseCase.UseCaseCallback<V>
    ) {
        mHandler.post { useCaseCallback.onSuccess(response) }
    }

    override fun <V : UseCase.ResponseValue> onError(
        useCaseCallback: UseCase.UseCaseCallback<V>, t: Throwable
    ) {
        mHandler.post { useCaseCallback.onError(t) }
    }

}