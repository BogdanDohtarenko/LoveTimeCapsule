package com.ideasapp.lovetimecapsule.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ideasapp.lovetimecapsule.domain.AddCapsuleUseCase
import com.ideasapp.lovetimecapsule.domain.Capsule
import com.ideasapp.lovetimecapsule.domain.ListCapsuleUseCase
import com.ideasapp.lovetimecapsule.domain.ShowCapsuleUseCase

class MainViewModel: ViewModel() {

    private val repository = com.ideasapp.lovetimecapsule.data.RepositoryImpl
    private val addCapsuleUseCase = AddCapsuleUseCase(repository)
    private val listCapsuleUseCase = ListCapsuleUseCase(repository)
    private val showCapsuleUseCase = ShowCapsuleUseCase(repository)

    private val _capsuleList = MutableLiveData<List<Capsule>>(mutableListOf())
    val capsuleList: LiveData<List<Capsule>>
        get() = _capsuleList

    private val _capsuleOpened = MutableLiveData<Capsule?>(null)
    val capsuleOpened: LiveData<Capsule?>
        get() = _capsuleOpened

    init {
        _capsuleList.value = listCapsuleUseCase.invoke()
    }

    fun saveCapsule(newCapsule: Capsule) {
        //save to LD
        val oldList = capsuleList.value?.toMutableList() ?: mutableListOf()
        _capsuleList.value = (oldList + newCapsule).toList()
        //save to DB
        addCapsuleUseCase.invoke(newCapsule)
    }
}