package com.android.data.source.local.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.data.source.local.base.AppDatabase.Companion.DB_VERSION
import com.android.data.model.PhotoResponse
import com.android.data.source.local.base.dao.PhotoDao

/**
 * To manage data items that can be accessed and updated
 * & also maintain relationships between them
 *
 * @Created by ZARA
 */
@Database(entities = [PhotoResponse::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val photoDao : PhotoDao

    companion object {
        const val DB_NAME = "PhotoGalleryApp.db"
        const val DB_VERSION = 1
    }
}