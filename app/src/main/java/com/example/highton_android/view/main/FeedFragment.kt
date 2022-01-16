package com.example.highton_android.view.main

import androidx.navigation.fragment.findNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FeedFragment : BaseFragment<FragmentFeedBinding>(R.layout.fragment_feed) {
    override fun FragmentFeedBinding.onViewCreated() {
        binding.imageView.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_feedInforFragment)
        }
    }
}