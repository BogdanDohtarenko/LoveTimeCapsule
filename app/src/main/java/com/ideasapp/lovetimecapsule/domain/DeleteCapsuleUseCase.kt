package com.ideasapp.lovetimecapsule.domain

import io.reactivex.rxjava3.core.Completable

class DeleteCapsuleUseCase(private val repository:Repository) {
    operator fun invoke(capsuleId: Int): Completable {
        return repository.deleteCapsule(capsuleId)
    }
}