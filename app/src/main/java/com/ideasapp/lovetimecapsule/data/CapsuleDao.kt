package com.ideasapp.lovetimecapsule.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CapsuleDao {
    @Query("SELECT * FROM Capsules")
    fun getCapsuleList():Single<List<CapsuleDbModel>> // Use Single for one-time results

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCapsule(newCapsuleDbModel: CapsuleDbModel):Completable
}