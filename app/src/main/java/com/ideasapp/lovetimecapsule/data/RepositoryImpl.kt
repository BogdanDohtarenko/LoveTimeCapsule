package com.ideasapp.lovetimecapsule.data

import com.ideasapp.lovetimecapsule.domain.Capsule
import com.ideasapp.lovetimecapsule.domain.Repository

object RepositoryImpl: Repository {
    override fun addCapsule(newCapsule:Capsule) {
        //TODO("Not yet implemented")
    }

    override fun listCapsule():List<Capsule> {
        //TODO("Not yet implemented")
        return listOf(Capsule(0, 2,1, ""), Capsule(1, 1,2, ""))
    }

    override fun showCapsule():Capsule {
        //TODO("Not yet implemented")
        return Capsule(2, 1, 2,"")
    }
}