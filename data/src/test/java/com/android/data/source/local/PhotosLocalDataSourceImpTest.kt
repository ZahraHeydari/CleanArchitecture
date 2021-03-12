package com.android.data.source.local

import com.android.data.model.PhotoResponse
import com.android.data.source.local.base.AppDatabase
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PhotosLocalDataSourceImpTest{

    private lateinit var localDataSource: PhotosLocalDataSourceImp
    private val mockResult = mockk<List<PhotoResponse>>(relaxed = true)
    private val mockDatabase : AppDatabase = mockk()

    @Before
    fun setUp() {
        localDataSource = PhotosLocalDataSourceImp(mockDatabase)
    }

    @Test
    fun testGetPhotosLocalDataSource() {
        val result : List<PhotoResponse>?
        coEvery {  mockDatabase.photoDao.loadAll() } returns mockResult

        runBlocking {  result = localDataSource.getPhotos() }

        coVerify { mockDatabase.photoDao.loadAll()  }

        assertEquals(result , mockResult)
    }

}