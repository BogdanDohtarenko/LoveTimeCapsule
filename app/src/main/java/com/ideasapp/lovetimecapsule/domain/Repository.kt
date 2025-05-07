package com.ideasapp.lovetimecapsule.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun saveCapsule(newCapsule:Capsule): Completable
    fun listCapsule():Single<List<Capsule>>
    fun showCapsule(): Capsule
}