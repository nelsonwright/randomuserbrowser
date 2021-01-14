package com.example.youviewexercise

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youviewexercise.api.RandomUserRepository
import com.example.youviewexercise.api.RandomUserRepositoryImpl
import com.example.youviewexercise.api.RandomUserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MasterViewModel : ViewModel() {
    private val repo: RandomUserRepository = RandomUserRepositoryImpl(RandomUserService.create())
    val viewState: LiveData<MasterViewState>
        get() = mutableLiveData

    private var mutableLiveData = MutableLiveData<MasterViewState>()

    fun getListOfPeople() {
        viewModelScope.launch(Dispatchers.Main) {
            mutableLiveData.value = MasterViewState(loading = true)

            try {
                val personList = withContext(Dispatchers.IO) {
                    repo.getRandomUsers()
                }
                mutableLiveData.value = MasterViewState(persons = personList)

            } catch (exception: Exception) {
                Log.d("YV", "Error retrieving list :$exception")
                mutableLiveData.value = MasterViewState(loadingError = true)
            }
        }
    }
}
