package com.example.youviewexercise.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youviewexercise.MasterViewState
import com.example.youviewexercise.api.RandomUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MasterViewModel(
    private val repository: RandomUserRepository,
    private val mainDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    val viewState: LiveData<MasterViewState>
        get() = mutableLiveData

    private var mutableLiveData = MutableLiveData<MasterViewState>()

    fun getListOfPeople() {
        viewModelScope.launch(mainDispatcher) {
            mutableLiveData.value = MasterViewState(loading = true)

            try {
                val personList = withContext(ioDispatcher) {
                    repository.getRandomUsers()
                }
                mutableLiveData.value = MasterViewState(persons = personList)

            } catch (exception: Exception) {
                mutableLiveData.value = MasterViewState(loadingError = true)
            }
        }
    }
}
