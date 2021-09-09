package com.example.dogapi.di

import com.example.dogapi.CatBreedFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface AppComponent {
    fun inject(catBreedFragment: CatBreedFragment)
}