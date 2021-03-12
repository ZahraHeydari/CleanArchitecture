package com.android.data.repository

import com.android.data.model.PhotoResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test


class PhotosRepositoryImpTest {

    @MockK
    lateinit var photosRepository: PhotosRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun testOnlinePhotosData() = runBlocking {
        val photos = mockk<List<PhotoResponse>>(relaxed = true)
        coEvery {
            photosRepository.getPhotoList(true)
        } returns (photos.map { it.toDomain() })

        val result = photosRepository.getPhotoList(true)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$photos] must be matches on each other!",
            result,
            CoreMatchers.`is`(photos)
        )
    }

    @Test
    fun testOfflinePhotosData() = runBlocking {
        val photos = mockk<List<PhotoResponse>>(relaxed = true)
        coEvery {
            photosRepository.getPhotoList(false)
        } returns (photos.map { it.toDomain() })

        val result = photosRepository.getPhotoList(false)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$photos] must be matches on each other!",
            result,
            CoreMatchers.`is`(photos)
        )
    }
}