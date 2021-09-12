package com.example.dogapi.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dogapi.api.ApiServiceInterface
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DataRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val apiServiceInterface: ApiServiceInterface = mockk()
    private val dataRepository = DataRepository(apiServiceInterface)

    @Test
    fun fetchCatBreeds() {
        runBlockingTest {
            dataRepository.getCatBreeds()
            coVerify { apiServiceInterface.getCatBreeds() }
        }
    }
}