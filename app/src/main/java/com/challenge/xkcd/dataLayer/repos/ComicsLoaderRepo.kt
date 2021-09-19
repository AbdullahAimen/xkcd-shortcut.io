package com.challenge.xkcd.dataLayer.repos

import com.challenge.xkcd.dataLayer.dataModel.Comic
import com.challenge.xkcd.dataLayer.dataSource.ComicsDataSource
import javax.inject.Inject

class ComicsLoaderRepo @Inject constructor(
    private val comicsDataSource: ComicsDataSource
) : ComicsDataSource {
    override fun getComicById(comicId: Int, callback: ComicsDataSource.FetchComicCallBack) {
        comicsDataSource.getComicById(comicId, callback)
    }

    override fun getComicsList(): List<Comic> {
        return comicsDataSource.getComicsList()
    }


    override fun saveComic(comic: Comic) {
        comicsDataSource.saveComic(comic)
    }

}