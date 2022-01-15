package com.example.highton_android.view.main

import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.highton_android.R
import com.example.highton_android.adapter.DiaryAdapter
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.databinding.FragmentDiaryBinding
import com.example.highton_android.viewmodel.DiaryViewModel
import com.example.highton_android.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiaryFragment : BaseFragment<FragmentDiaryBinding>(R.layout.fragment_diary) {

    private val viewModel: AuthViewModel by viewModels()
    private var token = ""
    private val diaryViewModel: DiaryViewModel by viewModels()
    private val diaryAdapter: DiaryAdapter by lazy {
        DiaryAdapter()
    }

    override fun FragmentDiaryBinding.onCreateView() {
        getToken()
    }

    override fun FragmentDiaryBinding.onViewCreated() {
        setAdapter()
        with(diaryViewModel){
            lifecycleScope.launch {
                getDiary(token)
            }
            diaryData.observe(viewLifecycleOwner){
                diaryAdapter.setData(it)

            }
        }
    }
    fun getToken(){
        viewModel.readToken.asLiveData().observe(viewLifecycleOwner){
            token=it.token
        }
    }

    private fun setAdapter() {
        binding.diaryRecycler.apply {
            adapter = diaryAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}