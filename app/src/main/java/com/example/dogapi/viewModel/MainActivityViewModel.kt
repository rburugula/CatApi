package com.example.dogapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogapi.data.DataRepository
import com.example.dogapi.model.CatBreed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {
    private val _catBreedsLiveData = MutableLiveData<Result<ArrayList<CatBreed>>>()
    val catBreedsLiveData: LiveData<Result<ArrayList<CatBreed>>> = _catBreedsLiveData

    fun fetchCatBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            _catBreedsLiveData.postValue(dataRepository.getCatBreeds())
        }
    }
}