package com.example.dogapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.dogapi.data.DataRepository
import com.example.dogapi.model.CatBreed
import com.example.dogapi.model.CatImage
import com.example.dogapi.viewModel.MainActivityViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertTrue
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
    private var observer: Observer<Result<List<CatBreed>>> = mockk(relaxed = true)

    @Test
    fun fetchCatBreeds() {
        testCoroutineScope.launch {
            mainActivityViewModel.fetchCatBreeds()
            coVerify { dataRepository.getCatBreeds() }
        }
    }

    @Test
    fun `WHEN fetchCatBreeds is successful THEN call once the corresponding repository and the success data`() {
        coEvery { dataRepository.getCatBreeds() } returns Result.success(buildFakeCatBreeds())
        mainActivityViewModel.catBreedsLiveData.observeForever { observer }

        testCoroutineScope.launch {
            mainActivityViewModel.fetchCatBreeds()

            coVerify(exactly = 1) { dataRepository.getCatBreeds() }

            val slot = slot<Result<ArrayList<CatBreed>>>()
            verify { observer.onChanged(capture(slot)) }
            assertTrue(slot.captured.isSuccess)

            mainActivityViewModel.catBreedsLiveData.removeObserver(observer)
        }
    }

    private fun buildFakeCatBreeds() = arrayListOf(
        CatBreed(
            name = "myName",
            image = CatImage(
                url = "url",
                height = 10,
                width = 10
            ),
            temperament = "temprerament",
            wikipedia_url = "wikiurl",
            energy_level = 2
        )
    )
}