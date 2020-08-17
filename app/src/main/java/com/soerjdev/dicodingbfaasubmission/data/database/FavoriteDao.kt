package com.soerjdev.dicodingbfaasubmission.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites_table WHERE id = :user_id")
    fun selectDetailUser(user_id: Int): LiveData<FavoriteModel>

    @Query("SELECT * FROM favorites_table")
    fun selectAllUser(): LiveData<List<FavoriteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(favoriteModel: FavoriteModel)

    @Query("DELETE FROM favorites_table WHERE id = :user_id")
    fun deleteUser(user_id: Int): Int

}