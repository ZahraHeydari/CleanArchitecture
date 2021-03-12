package com.android.data.source.local.base.dao

import androidx.room.*
import com.android.data.model.PhotoResponse

/**
 * Provides access to Photo underlying database
 * */
@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<PhotoResponse>?)

    @Query("SELECT * FROM PhotoResponse")
    suspend fun loadAll(): List<PhotoResponse>?

}