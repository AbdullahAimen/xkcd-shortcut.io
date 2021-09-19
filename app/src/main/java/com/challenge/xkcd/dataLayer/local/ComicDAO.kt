package com.challenge.xkcd.dataLayer.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.xkcd.dataLayer.dataModel.Comic

@Dao
interface ComicDAO {
    @Query("SELECT * FROM Comic ORDER BY num ASC")
    fun getComicsList(): List<Comic>

    @Query("SELECT * FROM Comic WHERE num like :comicId")
    fun getComicById(comicId: Int): Comic?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComic(comic: Comic)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComic(comic: List<Comic>)
}