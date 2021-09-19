package com.challenge.xkcd.dataLayer.local

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.challenge.xkcd.dataLayer.dataModel.Comic
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ComicsRoomDatabaseTest {
    lateinit var mComicDAO: ComicDAO
    lateinit var database: ComicsRoomDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, ComicsRoomDatabase::class.java).build()
        mComicDAO = database.comicDAODao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertedAndRetrievedComicsMatch() {
        val comics = (1..2).map {
            Comic(
                it,
                "month#$it",
                "",
                "2006",
                "test",
                "testTitle#$it",
                "",
                "",
                "",
                "testTitle#$it",
                "it",
                it % 2 == 0
            )
        }
        mComicDAO.insertComic(comics[0])
        mComicDAO.insertComic(comics[1])
        val allComics = mComicDAO.getComicsList()
        Assert.assertEquals(comics.size, allComics.size)
        Assert.assertEquals(comics[0].title, comics[0].title)
    }

    @Test
    fun testInsertConflictingComicsMatch() {
        val comics = (1..2).map {
            Comic(
                it,
                "month#$it",
                "",
                "2006",
                "test",
                "testTitle#$it",
                "",
                "",
                "",
                "testTitle#$it",
                "it",
                it % 2 == 0
            )
        }
        mComicDAO.insertComic(comics[0])
        mComicDAO.insertComic(comics[1])
        mComicDAO.insertComic(comics[1])

        val allComics = mComicDAO.getComicsList()
        Assert.assertEquals(comics.size, allComics.size)
    }

    @Test
    fun testInsertingNonOrderedList() {
        val comics = (12 downTo 1).map {
            Comic(
                it,
                "month#$it",
                "",
                "2006",
                "test",
                "testTitle#$it",
                "",
                "",
                "",
                "testTitle#$it",
                "it",
                it % 2 == 0
            )
        }
        mComicDAO.insertAllComic(comics)

        val allComics = mComicDAO.getComicsList()
        Assert.assertNotEquals(comics[0].num, allComics[0].num)
        Assert.assertEquals(comics[comics.size - 1].num, allComics[0].num)
    }
}