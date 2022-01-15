package com.example.highton_android.view.main.home

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.data.model.post.PopularFeed
import com.example.highton_android.databinding.FragmentHomeBinding
import com.example.highton_android.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val recommendationListAdapter by lazy { RecommendationListAdapter() }
    private val popularListAdapter by lazy { PopularListAdapter() }
    private val homeViewModel: HomeViewModel by viewModels()

    override fun FragmentHomeBinding.onCreateView() {
        setUpRecyclerView()

        setData()
        observeData()
    }

    private fun observeData() {
        homeViewModel.recommendationResult.observe(viewLifecycleOwner, { result ->
            when (result) {
                is NetworkResult.Success -> {
                    recommendationListAdapter.setData(result.data!!.content)
                }
                is NetworkResult.Error -> {
                    Log.d("TAG", "observeData: ${result.message}")
                }
                is NetworkResult.Loading -> {

                }
            }
        })
    }

    private fun setData() {
        getRecommendationList()
        getPopularList()
    }

    private fun getRecommendationList() {
        if (recommendationListAdapter.itemCount == 0) {
            homeViewModel.getRecommendation()
        }
    }

    private fun getPopularList() {
        popularListAdapter.setData(listOf(
            PopularFeed("오늘자 OO 발표회 레전드"),
            PopularFeed("오늘 간식 선공개 ㅋㅋ"),
            PopularFeed("오늘 출몰한\n" +
                    "고양이 가족 \uD83D\uDC31"),
        ))
    }

    private fun setUpRecyclerView() {
        binding.followRecommendationList.adapter = recommendationListAdapter
        binding.todaysPopularFeedList.adapter = popularListAdapter
    }
}