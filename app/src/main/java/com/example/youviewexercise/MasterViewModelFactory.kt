package com.example.youviewexercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youviewexercise.api.RandomUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Inject

class MasterViewModelFactory @Inject constructor(
    private val repository: RandomUserRepository,
    private val mainDispatcher: MainCoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MasterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            MasterViewModel(
                repository = repository,
                mainDispatcher = mainDispatcher,
                ioDispatcher = ioDispatcher
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}