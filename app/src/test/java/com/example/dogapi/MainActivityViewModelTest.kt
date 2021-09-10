package com.example.dogapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dogapi.data.DataRepository
import com.example.dogapi.viewModel.MainActivityViewModel
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    private val dataRepository: DataRepository = mockk(relaxed = true)
    private val mainActivityViewModel = MainActivityViewModel(dataRepository)

    @Test
    fun fetchCatBreeds() {
        testCoroutineScope.launch {
            mainActivityViewModel.fetchCatBreeds()
            coVerify { dataRepository.getCatBreeds() }
        }
    }
}