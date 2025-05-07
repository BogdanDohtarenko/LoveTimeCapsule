package com.ideasapp.lovetimecapsule.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ideasapp.lovetimecapsule.domain.AddCapsuleUseCase
import com.ideasapp.lovetimecapsule.domain.Capsule
import com.ideasapp.lovetimecapsule.domain.ListCapsuleUseCase
import com.ideasapp.lovetimecapsule.domain.ShowCapsuleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = com.ideasapp.lovetimecapsule.data.RepositoryImpl(application)
    private val addCapsuleUseCase = AddCapsuleUseCase(repository)
    private val listCapsuleUseCase = ListCapsuleUseCase(repository)
    private val showCapsuleUseCase = ShowCapsuleUseCase(repository)

    private val _capsuleList = MutableLiveData<List<Capsule>>(mutableListOf())
    val capsuleList: LiveData<List<Capsule>>
        get() = _capsuleList

    private val _capsuleOpened = MutableLiveData<Capsule?>(null)
    val capsuleOpened: LiveData<Capsule?>
        get() = _capsuleOpened

    private val disposables = CompositeDisposable()

    init {
        val disposable = listCapsuleUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { capsules -> _capsuleList.value = capsules },
                { error -> Log.e("MainViewModel", "Error fetching capsules", error) }
            )
        disposables.add(disposable)
    }

    fun saveCapsule(newCapsule: Capsule) {
        val oldList = capsuleList.value?.toMutableList() ?: mutableListOf()
        _capsuleList.value = (oldList + newCapsule).toList()

        val disposable = addCapsuleUseCase.invoke(newCapsule)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("MainViewModel", "Capsule saved successfully") },
                { error -> Log.e("MainViewModel", "Error saving capsule", error) }
            )
        disposables.add(disposable)
    }
}