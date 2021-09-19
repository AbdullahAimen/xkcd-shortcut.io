package com.challenge.xkcd.dataLayer.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.xkcd.dataLayer.dataModel.Comic

@Database(entities = arrayOf(Comic::class), version = 1, exportSchema = false)
abstract class ComicsRoomDatabase : RoomDatabase() {
    abstract fun comicDAODao(): ComicDAO
}