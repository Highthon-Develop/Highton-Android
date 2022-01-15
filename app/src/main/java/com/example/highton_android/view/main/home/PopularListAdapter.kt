package com.example.highton_android.view.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.highton_android.data.model.post.PopularFeed
import com.example.highton_android.data.model.post.RecommendedPage
import com.example.highton_android.databinding.ItemTodaysPopularFeedListBinding

class PopularListAdapter : RecyclerView.Adapter<PopularViewHolder>() {
    private var popularList = emptyList<PopularFeed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder =
        PopularViewHolder.from(parent)

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) = holder.bind(popularList[position])

    override fun getItemCount(): Int = popularList.size

    fun setData(newList: List<PopularFeed>) {
        popularList = newList
        notifyDataSetChanged()
    }
}

class PopularViewHolder(private val binding: ItemTodaysPopularFeedListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(popularFeed: PopularFeed) {
        binding.textTitle.text = popularFeed.title
    }

    companion object {
        fun from(parent: ViewGroup): PopularViewHolder = PopularViewHolder(
            ItemTodaysPopularFeedListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}