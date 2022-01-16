package com.example.highton_android.view.main

import androidx.fragment.app.activityViewModels
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun FragmentProfileBinding.onCreateView() {
        with(binding) {
            with (mainViewModel.user.value!!.data!!) {
                textName.text = name
                textGrade.text = "${grade}학년"
                textSchool.text = "${school.name} 재학"
                follwerCount.text = "74"
                followingCount.text = "73"
            }
        }
    }
}