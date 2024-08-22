package com.catchallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.catchallenge.databinding.FragmentHomeBinding
import com.catchallenge.model.Breed
import com.catchallenge.utils.launchLifecycleAware
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        launchLifecycleAware {
            homeViewModel.homeStateFlow.collect()
            {
                handleState(it)
            }
        }

        homeViewModel.getBreeds()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(state: HomeState) {
        when (state) {
            HomeState.Loading -> setUiLoading()
            HomeState.NoData -> setUiNoData()
            HomeState.Error -> setUiError()
            is HomeState.BreedList -> setUiData(state.breedList)
        }
    }

    private fun setUiLoading() {
    }

    private fun setUiNoData() {
    }

    private fun setUiError() {

    }

    private fun setUiData(list: List<Breed>) {

    }
}