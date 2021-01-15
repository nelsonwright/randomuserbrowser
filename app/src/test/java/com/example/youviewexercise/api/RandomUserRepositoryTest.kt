package com.example.youviewexercise.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.youviewexercise.api.apimodels.ApiResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomUserRepositoryTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val mockApi = mockk<RandomUserApiInterface>()
    private lateinit var repo: RandomUserRepository

    @Before
    fun setup() {
        coEvery { mockApi.getRandomUsers() } returns ApiResult(results = listOf())
        repo = RandomUserRepositoryImpl(mockApi)
    }

    @Test
    fun shouldRequestRandomUsers() = runBlockingTest {
        repo.getRandomUsers()
        coVerify { mockApi.getRandomUsers() }
    }

}