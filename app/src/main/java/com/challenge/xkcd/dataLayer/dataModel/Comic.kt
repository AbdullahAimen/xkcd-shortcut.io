package com.challenge.xkcd.dataLayer.dataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Comic(
    @PrimaryKey
    val num: Int,
    val month: String?,
    val link: String?,
    val year: String?,
    val news: String?,
    val safe_title: String?,
    val transcript: String?,
    val alt: String?,
    val img: String?,
    val title: String?,
    val day: String?,
    var isFavorite: Boolean = false,

    ) {
    fun getTransScript() =
        transcript?.replace("[[", "")?.replace("]]", "")?.replace(Regex("\\{\\{.*\\.\\}\\}"), "")
}