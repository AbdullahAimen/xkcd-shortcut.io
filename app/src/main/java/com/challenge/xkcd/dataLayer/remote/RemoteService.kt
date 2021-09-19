package com.challenge.xkcd.dataLayer.remote

import com.challenge.xkcd.dataLayer.dataModel.Comic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RemoteService {
    @GET
    fun loadComic(@Url url: String): Call<Comic>
}