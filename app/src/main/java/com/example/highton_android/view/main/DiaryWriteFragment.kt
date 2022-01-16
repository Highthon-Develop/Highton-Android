package com.example.highton_android.view.main

import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.data.model.diary.PostDiaryRequest
import com.example.highton_android.databinding.FragmentWirteDiaryBinding
import com.example.highton_android.viewmodel.DiaryViewModel
import com.example.highton_android.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint

class DiaryWriteFragment : BaseFragment<FragmentWirteDiaryBinding>(R.layout.fragment_wirte_diary) {
    private val viewModel: DiaryViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()
    private var token =""
    override fun FragmentWirteDiaryBinding.onCreateView() {
        getToken()

    }

    override fun FragmentWirteDiaryBinding.onViewCreated() {
        with(viewModel) {
            binding.checkBtn.setOnClickListener {
                lifecycleScope.launch {
                    postDiary(token,PostDiaryRequest(binding.editText.text.toString()))
                }
            }
            isSuccess.observe(viewLifecycleOwner){
                if (it){
                    findNavController().navigate(R.id.action_diaryWriteFragment_to_diaryFragment)
                }
            }
        }
    }
    fun getToken(){
        authViewModel.readToken.asLiveData().observe(viewLifecycleOwner){
            token=it.token
        }
    }
}