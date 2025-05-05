package com.ideasapp.lovetimecapsule.data

import android.app.Application
import com.ideasapp.lovetimecapsule.domain.Capsule
import com.ideasapp.lovetimecapsule.domain.Repository

class RepositoryImpl(application: Application): Repository {

    private val dao = AppDatabase.getInstance(application).CapsuleDao()

    override fun addCapsule(newCapsule: Capsule) {
        val dbModel = CapsuleMapper.mapEntityToDbModel(capsule = newCapsule)
        dao.addCapsule(dbModel)
    }

    override fun listCapsule():List<Capsule> {
        val dbModelList = dao.getCapsuleList()
        val entityList = dbModelList.map { CapsuleMapper.mapDbModelToEntity(it) }
        return entityList
    }

    override fun showCapsule():Capsule {

        return Capsule(2, 1, 2,"")
    }
}