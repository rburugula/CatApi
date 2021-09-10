package com.example.dogapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.dogapi.databinding.FragmentCatBreedDetailBinding

class CatBreedDetailFragment : Fragment() {

    private val args: CatBreedDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentCatBreedDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatBreedDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.tvName.text = args.catBreed.name
        binding.tvTemperament.text = args.catBreed.temperament
        binding.tvWikiUrl.text = args.catBreed.wikipedia_url
        binding.tvEnergyLevel.text = args.catBreed.energy_level.toString()

        return view
    }
}