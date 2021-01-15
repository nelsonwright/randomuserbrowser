package com.example.youviewexercise.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youviewexercise.api.RandomUserRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MasterViewModelFactory @Inject constructor(
    private val repository: RandomUserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MasterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            MasterViewModel(
                repository = repository,
                mainDispatcher = Dispatchers.Main,
                ioDispatcher = Dispatchers.IO
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}