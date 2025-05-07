package com.ideasapp.lovetimecapsule.data

import android.app.Application
import com.ideasapp.lovetimecapsule.domain.Capsule
import com.ideasapp.lovetimecapsule.domain.Repository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryImpl(application: Application): Repository {

    private val dao = AppDatabase.getInstance(application).CapsuleDao()

    override fun saveCapsule(newCapsule: Capsule): Completable {
        val capsuleDbModel = CapsuleMapper.mapEntityToDbModel(newCapsule)
        return dao.addCapsule(capsuleDbModel) // Save to the database
            .subscribeOn(Schedulers.io()) // Run the query on the IO scheduler
    }

    override fun listCapsule(): Single<List<Capsule>> {
        return dao.getCapsuleList()
            .map { dbModelList ->
                dbModelList.map { CapsuleMapper.mapDbModelToEntity(it) }
            }
            .subscribeOn(Schedulers.io())
    }

    override fun showCapsule():Capsule {
        return Capsule(2, 1, 2,"")
    }
}