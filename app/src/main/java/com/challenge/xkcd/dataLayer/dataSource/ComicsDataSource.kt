package com.challenge.xkcd.dataLayer.dataSource

import com.challenge.xkcd.dataLayer.dataModel.Comic

interface ComicsDataSource {
    interface FetchComicCallBack {
        fun onComicLoaded(comic: Comic)
        fun onError(t: Throwable)
    }

    fun getComicById(comicId: Int, callback: FetchComicCallBack)
    fun getComicsList(): List<Comic>
    fun saveComic(comic: Comic)
}