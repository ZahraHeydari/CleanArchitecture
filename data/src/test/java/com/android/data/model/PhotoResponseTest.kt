package com.android.data.model

import junit.framework.Assert.assertEquals
import org.junit.Test


class PhotoResponseTest {


    private val photoResponse = PhotoResponse(
        1,
        1,
        "accusamus beatae ad facilis cum similique qui sunt",
        "https://via.placeholder.com/600/92c952",
        "https://via.placeholder.com/150/92c952"
    )

    @Test
    fun testAlbumId() {
        val photoResponseCopy = photoResponse.copy(albumId = 7)
        assertEquals(7, photoResponseCopy.albumId)
    }


    @Test
    fun testToDomain() {
        val photoEntity = photoResponse.toDomain()
        assertEquals(photoResponse.albumId, photoEntity.albumId)

    }

}