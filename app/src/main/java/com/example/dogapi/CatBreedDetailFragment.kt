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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatBreedDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            tvName.text = args.catBreed.name
            tvTemperament.text = args.catBreed.temperament
            tvWikiUrl.text = args.catBreed.wikipedia_url
            tvEnergyLevel.text = args.catBreed.energy_level.toString()
        }
        return view
    }
}