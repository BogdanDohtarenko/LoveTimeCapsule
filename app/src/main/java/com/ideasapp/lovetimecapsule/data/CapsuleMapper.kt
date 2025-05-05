package com.ideasapp.lovetimecapsule.data

import com.ideasapp.lovetimecapsule.domain.Capsule

object CapsuleMapper {
    fun mapEntityToDbModel(capsule: Capsule): CapsuleDbModel {
        return CapsuleDbModel(
            id = capsule.id,
            text = capsule.text,
            writingTime = capsule.writingTime,
            scheduledTime = capsule.scheduledTime,
        )
    }
    fun mapDbModelToEntity(capsuleDbModel: CapsuleDbModel): Capsule {
        return Capsule(
            id = capsuleDbModel.id,
            text = capsuleDbModel.text,
            writingTime = capsuleDbModel.writingTime,
            scheduledTime = capsuleDbModel.scheduledTime,
        )
    }
}