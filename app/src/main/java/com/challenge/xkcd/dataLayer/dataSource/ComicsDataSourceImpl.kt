package com.challenge.xkcd.dataLayer.dataSource

import com.challenge.xkcd.dataLayer.dataModel.Comic
import com.challenge.xkcd.dataLayer.local.ComicDAO
import com.challenge.xkcd.dataLayer.remote.RemoteService
import com.challenge.xkcd.domainLayer.base.UseCaseScheduler
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ComicsDataSourceImpl @Inject constructor(
    private val mRemoteService: RemoteService,
    private val mComicDAO: ComicDAO,
    private val mUseCaseScheduler: UseCaseScheduler
) : ComicsDataSource {
    override fun getComicById(comicId: Int, callback: ComicsDataSource.FetchComicCallBack) {
        mRemoteService
            .loadComic("https://xkcd.com/$comicId/info.0.json")
            .enqueue(object : retrofit2.Callback<Comic?> {
                override fun onResponse(
                    call: Call<Comic?>,
                    response: Response<Comic?>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        callback.onComicLoaded(response.body()!!)
                    } else {
                        callback.onError(Throwable(response.errorBody().toString()))
                    }
                }

                override fun onFailure(call: Call<Comic?>, t: Throwable) {
                    mUseCaseScheduler.execute {
                        val comic = mComicDAO.getComicById(comicId)
                        if (comic != null)
                            callback.onComicLoaded(comic)
                        else
                            callback.onError(t)
                    }

                }

            })
    }

    override fun getComicsList(): List<Comic> {
        return mComicDAO.getComicsList()
    }

    override fun saveComic(comic: Comic) {
        mUseCaseScheduler.execute { mComicDAO.insertComic(comic) }
    }
}