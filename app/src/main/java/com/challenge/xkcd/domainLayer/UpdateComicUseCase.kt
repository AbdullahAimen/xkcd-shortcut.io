package com.challenge.xkcd.domainLayer

import com.challenge.xkcd.dataLayer.dataModel.Comic
import com.challenge.xkcd.dataLayer.repos.ComicsLoaderRepo
import com.challenge.xkcd.domainLayer.base.UseCase
import javax.inject.Inject

class UpdateComicUseCase @Inject constructor(
    private val mComicsLoaderRepo: ComicsLoaderRepo
) : UseCase<UpdateComicUseCase.RequestValues, UpdateComicUseCase.ResponseValue>() {
    class RequestValues(val comic: Comic) : UseCase.RequestValues
    class ResponseValue : UseCase.ResponseValue

    override fun executeUseCase(requestValues: RequestValues?) {
        mComicsLoaderRepo.saveComic(requestValues?.comic!!)
        useCaseCallback?.onSuccess(ResponseValue())
    }

}