package com.example.dogapi

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapi.adapter.RecyclerViewAdapter
import com.example.dogapi.databinding.FragmentCatBreedBinding
import com.example.dogapi.model.CatBreed
import com.example.dogapi.viewModel.MainActivityViewModel
import javax.inject.Inject

class CatBreedFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    private lateinit var binding: FragmentCatBreedBinding

    private lateinit var recyclerAdapter: RecyclerViewAdapter
    private lateinit var catBreeds: ArrayList<CatBreed>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatBreedBinding.inflate(inflater, container, false)
        val view = binding.root
        initView()
        initViewModel()
        return view
    }

    private fun initView() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter
        recyclerAdapter.onItemClick = { CatBreed ->
            val action = CatBreedFragmentDirections.actionCatBreedFragmentToCatBreedDetailFragment()
            findNavController().navigate(action)
        }
    }

    private fun initViewModel() {
        viewModel.catBreedsLiveData.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                catBreeds = it
                recyclerAdapter.setUpdatedData(it)
            }.onFailure {
                showErrrorMessage()
            }
        }
        viewModel.fetchCatBreeds()
    }

    private fun showErrrorMessage() {
        Toast.makeText(context, getString(R.string.error_connecting_to_server), Toast.LENGTH_LONG)
            .show()
    }
}