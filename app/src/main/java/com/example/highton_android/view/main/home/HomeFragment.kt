package com.example.highton_android.view.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.data.model.post.PopularFeed
import com.example.highton_android.data.model.post.RecommendedPage
import com.example.highton_android.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val recommendationListAdapter by lazy { RecommendationListAdapter() }
    private val popularListAdapter by lazy { PopularListAdapter() }

    override fun FragmentHomeBinding.onCreateView() {
        setUpRecyclerView()
        recommendationListAdapter.setData(listOf(
            RecommendedPage("내 이름은 자이언티"),
            RecommendedPage("서울시 강동구에 살아"),
            RecommendedPage("나는 문어"),
            RecommendedPage("꿈을 꾸는 문어"),
        ))
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