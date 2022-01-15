package com.example.highton_android.view.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.highton_android.data.model.recommendation.User
import com.example.highton_android.databinding.ItemFollowRecommendationListBinding

class RecommendationListAdapter : RecyclerView.Adapter<RecommendationViewHolder>() {
    private var recommendationList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder =
        RecommendationViewHolder.from(parent)

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) = holder.bind(recommendationList[position])

    override fun getItemCount(): Int = recommendationList.size

    fun setData(newList: List<User>) {
        recommendationList = newList
        notifyDataSetChanged()
    }
}

class RecommendationViewHolder(private val binding: ItemFollowRecommendationListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(recommendation: User) {
        binding.textTitle.text = recommendation.nickname
    }

    companion object {
        fun from(parent: ViewGroup): RecommendationViewHolder = RecommendationViewHolder(
            ItemFollowRecommendationListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}
