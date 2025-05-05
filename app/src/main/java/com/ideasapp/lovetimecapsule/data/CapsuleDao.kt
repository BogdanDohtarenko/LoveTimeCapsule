package com.ideasapp.lovetimecapsule.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CapsuleDao {
    @Query("SELECT * FROM Capsules")
    fun getCapsuleList(): List<CapsuleDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCapsule(newCapsuleDbModel: CapsuleDbModel)

    @Query("SELECT * FROM Capsules")
    fun getIdeaList(): List<CapsuleDbModel>
}