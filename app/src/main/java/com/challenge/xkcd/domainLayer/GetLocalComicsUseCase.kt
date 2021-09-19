package com.challenge.xkcd.domainLayer

import com.challenge.xkcd.dataLayer.dataModel.Comic
import com.challenge.xkcd.dataLayer.repos.ComicsLoaderRepo
import com.challenge.xkcd.domainLayer.base.UseCase
import javax.inject.Inject

class GetLocalComicsUseCase @Inject constructor(
    private val mComicsLoaderRepo: ComicsLoaderRepo
) : UseCase<GetLocalComicsUseCase.RequestValues, GetLocalComicsUseCase.ResponseValue>() {
    class RequestValues : UseCase.RequestValues
    class ResponseValue(val mComicResponse: List<Comic>) : UseCase.ResponseValue

    override fun executeUseCase(requestValues: RequestValues?) {
        val localComicsList = mComicsLoaderRepo.getComicsList()
        if (localComicsList.isNullOrEmpty())
            useCaseCallback?.onError(Throwable("empty List"))
        else
            useCaseCallback?.onSuccess(ResponseValue(localComicsList))

    }

}