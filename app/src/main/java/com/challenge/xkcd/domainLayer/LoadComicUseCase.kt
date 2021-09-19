package com.challenge.xkcd.domainLayer

import com.challenge.xkcd.dataLayer.dataModel.Comic
import com.challenge.xkcd.dataLayer.dataSource.ComicsDataSource
import com.challenge.xkcd.dataLayer.repos.ComicsLoaderRepo
import com.challenge.xkcd.domainLayer.base.UseCase
import javax.inject.Inject

class LoadComicUseCase @Inject constructor(
    private val mComicsLoaderRepo: ComicsLoaderRepo
) : UseCase<LoadComicUseCase.RequestValues, LoadComicUseCase.ResponseValue>() {
    class RequestValues(val comicId: Int) : UseCase.RequestValues
    class ResponseValue(val mComicResponse: Comic) : UseCase.ResponseValue

    override fun executeUseCase(requestValues: RequestValues?) {
        mComicsLoaderRepo.getComicById(
            requestValues?.comicId ?: 1,
            object : ComicsDataSource.FetchComicCallBack {
                override fun onComicLoaded(comic: Comic) {
                    useCaseCallback?.onSuccess(ResponseValue(comic))
                    mComicsLoaderRepo.saveComic(comic)
                }

                override fun onError(t: Throwable) {
                    useCaseCallback?.onError(t)
                }
            })
    }

}