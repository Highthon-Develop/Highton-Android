package com.example.highton_android.view.main

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.data.model.diary.PostDiaryRequest
import com.example.highton_android.databinding.FragmentWirteDiaryBinding
import com.example.highton_android.viewmodel.DiaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint

class DiaryWriteFragment : BaseFragment<FragmentWirteDiaryBinding>(R.layout.fragment_wirte_diary) {
    private val viewModel: DiaryViewModel by viewModels()
    override fun FragmentWirteDiaryBinding.onCreateView() {

    }

    override fun FragmentWirteDiaryBinding.onViewCreated() {
        with(viewModel) {
            binding.checkBtn.setOnClickListener {
                lifecycleScope.launch {
                    postDiary(PostDiaryRequest(binding.contentEditText.text.toString()))
                }
            }
            isSuccess.observe(viewLifecycleOwner){
                if (it){
                    findNavController().navigate(R.id.action_diaryWriteFragment_to_diaryFragment)
                }
            }
        }
    }
}