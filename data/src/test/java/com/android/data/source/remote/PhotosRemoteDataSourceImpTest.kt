package com.android.data.source.remote

import com.android.data.model.PhotoResponse
import com.android.data.source.remote.base.ApiService
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class PhotosRemoteDataSourceImpTest {

    private lateinit var remoteDataSource: PhotosRemoteDataSourceImp
    private val mockResult = mockk<List<PhotoResponse>>(relaxed = true)
    private val mockApiService: ApiService = mockk()

    @Before
    fun setUp() {
        remoteDataSource = PhotosRemoteDataSourceImp(mockApiService)
    }

    @Test
    fun testGetPhotosRemoteDataSource() {
        val result : List<PhotoResponse>?
        coEvery { mockApiService.getPhotos() } returns mockResult

        runBlocking {  result = remoteDataSource.getPhotos() }

        coVerify { mockApiService.getPhotos() }

        assertEquals(result , mockResult)
    }
}