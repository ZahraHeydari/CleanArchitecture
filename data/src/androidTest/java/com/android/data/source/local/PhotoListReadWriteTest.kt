package com.android.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.data.model.PhotoResponse
import com.android.data.source.local.base.AppDatabase
import com.android.data.source.local.base.dao.PhotoDao
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PhotoListReadWriteTest {

    private lateinit var photoDao: PhotoDao
    private lateinit var database : AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        photoDao = database.photoDao
    }

    @Test
    @Throws(Exception::class)
    fun testWritePhotoListAndRead() {
        val mockedPhoto = mockk<PhotoResponse>(relaxed = true)
        val photoList = mutableListOf(mockedPhoto)

        runBlocking {
            photoDao.insertAll(photoList)
        }

        runBlocking {
            val loadedPhotoList = photoDao.loadAll()
            assertThat(loadedPhotoList?.size, equalTo(1))
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }
}