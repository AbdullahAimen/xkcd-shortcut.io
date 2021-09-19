package com.challenge.xkcd.util

import com.challenge.xkcd.dataLayer.dataModel.Comic


sealed class ComicCommand {
    data class AssignComicResult(val mComic: Comic) : ComicCommand()
    data class OnErrorLoading(val error: Throwable) : ComicCommand()
    object showLoading : ComicCommand()
    object hideLoading : ComicCommand()
    object disableNext : ComicCommand()
    object enableNext : ComicCommand()
    object disableFirst : ComicCommand()
    object enableFirst : ComicCommand()
    object assignFavorite : ComicCommand()
    object removeFavorite : ComicCommand()

}