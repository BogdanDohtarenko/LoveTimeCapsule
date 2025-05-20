package com.ideasapp.lovetimecapsule.presentation

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ideasapp.lovetimecapsule.domain.AddCapsuleUseCase
import com.ideasapp.lovetimecapsule.domain.Capsule
import com.ideasapp.lovetimecapsule.domain.ListCapsuleUseCase
import com.ideasapp.lovetimecapsule.domain.DeleteCapsuleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = com.ideasapp.lovetimecapsule.data.RepositoryImpl(application)
    private val addCapsuleUseCase = AddCapsuleUseCase(repository)
    private val listCapsuleUseCase = ListCapsuleUseCase(repository)
    private val deleteCapsuleUseCase = DeleteCapsuleUseCase(repository)

    private val _capsuleList = MutableLiveData<List<Capsule>>(mutableListOf())
    val capsuleList: LiveData<List<Capsule>>
        get() = _capsuleList

    private val sharedPreferences by lazy {
        application.getSharedPreferences(CapsuleReceiver.SHARED_PREF_FILE, Context.MODE_PRIVATE)
    }

    private val _capsuleOpened = MutableLiveData<String?>(sharedPreferences.getString(CapsuleReceiver.CAPSULE_TEXT_KEY, ""))
    val capsuleOpened: LiveData<String?>
        get() = _capsuleOpened

    private val disposables = CompositeDisposable()
    private val alarmManager = application.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    init {
        deleteCapsule(capsuleOpened.value ?: "")
        val disposable = listCapsuleUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { capsules -> _capsuleList.value = capsules },
                { error -> Log.e("MainViewModel", "Error fetching capsules", error) }
            )
        disposables.add(disposable)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun saveCapsule(newCapsule: Capsule) {
        val oldList = capsuleList.value?.toMutableList() ?: mutableListOf()
        _capsuleList.value = (oldList + newCapsule).toList()

        val disposable = addCapsuleUseCase.invoke(newCapsule)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("MainViewModel", "Capsule saved successfully")
                    scheduleCapsuleOpening(newCapsule)
                },
                { error -> Log.e("MainViewModel", "Error saving capsule", error) }
            )
        disposables.add(disposable)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun scheduleCapsuleOpening(capsule: Capsule) {
        try {
            if (alarmManager.canScheduleExactAlarms()) {
                val intent = Intent(getApplication(), CapsuleReceiver::class.java).apply {
                    putExtra("capsule_id", capsule.id)
                    putExtra("capsule_text", capsule.text)
                }

                val pendingIntent = PendingIntent.getBroadcast(
                    getApplication(),
                    capsule.id,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    capsule.scheduledTime,
                    pendingIntent
                )
                Log.d("MainViewModel", "Capsule opening scheduled for: ${capsule.scheduledTime}")
            } else {
                Log.e("MainViewModel", "Cannot schedule exact alarms. Permission not granted.")
            }
        } catch (e: SecurityException) {
            Log.e("MainViewModel", "Failed to schedule exact alarm due to security exception", e)
        }
    }

    fun deleteCapsule(oldCapsuleText: String) {
        val oldList = capsuleList.value?.toMutableList() ?: mutableListOf()
        val oldCapsule = oldList.find { it.text == oldCapsuleText } ?: return
        _capsuleList.value = (oldList - oldCapsule).toList()
        val disposable = deleteCapsuleUseCase.invoke(capsuleId = oldCapsule.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("MainViewModel", "Capsule deleted successfully") },
                { error -> Log.e("MainViewModel", "Error deleted capsule", error) }
            )
        disposables.add(disposable)
    }

    fun openCapsule(capsule: String) {
        _capsuleOpened.value = capsule
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}