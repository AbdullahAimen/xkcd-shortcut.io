package com.challenge.xkcd.presentation

import androidx.lifecycle.MutableLiveData
import com.challenge.xkcd.dataLayer.dataModel.Comic
import com.challenge.xkcd.domainLayer.LoadComicUseCase
import com.challenge.xkcd.domainLayer.UpdateComicUseCase
import com.challenge.xkcd.domainLayer.base.UseCase
import com.challenge.xkcd.domainLayer.base.UseCaseHandler
import com.challenge.xkcd.util.ComicCommand
import com.challenge.xkcd.util.EspressoIdlingResource
import com.challenge.xkcd.util.Event
import javax.inject.Inject

class ComicPresenter @Inject constructor(
    private val mUpdateComicUseCase: UpdateComicUseCase,
    private val mLoadComicUseCase: LoadComicUseCase,
    private val mUseCaseHandler: UseCaseHandler
) {
    var mComicCommand = MutableLiveData<Event<ComicCommand>>()
    private var comicId = 1
    private lateinit var currentComic: Comic

    fun loadFirstComic() {
        mComicCommand.value = Event(ComicCommand.showLoading)
        comicId = 1
        loadComic()
    }

    fun loadLastComic() {
        mComicCommand.value = Event(ComicCommand.showLoading)
        comicId = 2516
        loadComic()
    }

    fun loadNextComic() {
        mComicCommand.value = Event(ComicCommand.showLoading)
        if (comicId < 2516) {
            comicId += 1
            if (comicId == 2516)
                mComicCommand.value =
                    Event(ComicCommand.disableNext)
            else
                mComicCommand.value =
                    Event(ComicCommand.enableNext)
        } else {
            mComicCommand.value =
                Event(ComicCommand.disableNext)
            return
        }
        loadComic()
    }

    fun loadPreviousComic() {
        mComicCommand.value = Event(ComicCommand.showLoading)
        if (comicId > 1) {
            comicId -= 1
            if (comicId == 1)
                mComicCommand.value =
                    Event(ComicCommand.disableFirst)
            else
                mComicCommand.value =
                    Event(ComicCommand.enableFirst)
        } else {
            mComicCommand.value =
                Event(ComicCommand.disableFirst)
            return
        }
        loadComic()
    }

    private fun loadComic() {
        EspressoIdlingResource.increment()
        mUseCaseHandler.execute(
            mLoadComicUseCase, LoadComicUseCase.RequestValues(comicId),
            object : UseCase.UseCaseCallback<LoadComicUseCase.ResponseValue> {
                override fun onSuccess(response: LoadComicUseCase.ResponseValue) {
                    currentComic = response.mComicResponse
                    mComicCommand.value =
                        Event(ComicCommand.AssignComicResult(currentComic))
                    EspressoIdlingResource.decrement()
                }

                override fun onError(t: Throwable) {
                    mComicCommand.value = Event(ComicCommand.OnErrorLoading(t))
                    EspressoIdlingResource.decrement()
                }
            })
    }

    fun changeFavorite() {
        currentComic.isFavorite = !currentComic.isFavorite
        EspressoIdlingResource.increment()
        mUseCaseHandler.execute(
            mUpdateComicUseCase,
            UpdateComicUseCase.RequestValues(currentComic),
            object : UseCase.UseCaseCallback<UpdateComicUseCase.ResponseValue> {
                override fun onSuccess(response: UpdateComicUseCase.ResponseValue) {
                    if (currentComic.isFavorite)
                        mComicCommand.value = Event(ComicCommand.assignFavorite)
                    else
                        mComicCommand.value = Event(ComicCommand.removeFavorite)
                    EspressoIdlingResource.decrement()
                }

                override fun onError(t: Throwable) {
                    EspressoIdlingResource.decrement()
                }
            })
    }
}