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
    fun getCapsuleList():Single<List<CapsuleDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCapsule(newCapsuleDbModel: CapsuleDbModel):Completable

    @Query("DELETE FROM Capsules WHERE :id = id")
    fun deleteCapsule(id: Int):Completable
}