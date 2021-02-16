/*
 * 31.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from databaseEntity")
    fun getUsers(): LiveData<List<DatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseEntity)
}

@Database(
    entities = [
        DatabaseEntity::class
    ], version = 1
)
abstract class EntityDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}

private lateinit var INSTANCE: EntityDatabase

fun getDatabase(context: Context): EntityDatabase {
    synchronized(EntityDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                EntityDatabase::class.java,
                "videos"
            ).build()
        }
    }
    return INSTANCE
}