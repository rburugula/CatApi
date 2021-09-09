package com.example.dogapi

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogapi.databinding.FragmentCatBreedBinding

class CatBreedFragment : Fragment() {

    private  var _binding: FragmentCatBreedBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCatBreedBinding.inflate(inflater, container, false)
        val view = binding.root

        return view

    }
}