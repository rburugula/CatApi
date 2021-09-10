package com.example.dogapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dogapi.api.ApiServiceInterface
import com.example.dogapi.data.DataRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DataRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    private val apiServiceInterface: ApiServiceInterface = mockk()
    private val dataRepository = DataRepository(apiServiceInterface)

    @Test
    fun fetchCatBreeds() {
        testCoroutineScope.launch {
            dataRepository.getCatBreeds()
            coVerify { apiServiceInterface.getCatBreeds() }
        }
    }
}