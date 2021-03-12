package com.android.domain.usecase

import com.android.domain.model.PhotoEntity
import com.android.domain.repository.PhotosRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class GetPhotosUseCaseTest {

    private lateinit var usecase: GetPhotosUseCase
    private val mockRepository: PhotosRepository = mockk()
    private val mockResult = mockk<List<PhotoEntity>>(relaxed = true)

    @Before
    fun setUp() {
        usecase = GetPhotosUseCase(mockRepository)
    }

    @Test
    fun testGetOnlinePhotosUseCase() {
        val result: List<PhotoEntity>?

        coEvery {
            mockRepository.getPhotoList(true)
        } returns (mockResult)

        runBlocking { result = usecase.run(true) }

        coVerify { mockRepository.getPhotoList(true) }
        assertEquals(result, mockResult)
    }


    @Test
    fun testGetOfflinePhotosUseCase() {
        val result: List<PhotoEntity>?

        coEvery {
            mockRepository.getPhotoList(false)
        } returns (mockResult)

        runBlocking { result = usecase.run(false) }

        coVerify { mockRepository.getPhotoList(false) }
        assertEquals(result, mockResult)
    }
}