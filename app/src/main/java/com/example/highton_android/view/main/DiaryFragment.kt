package com.example.highton_android.view.main

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
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

            diaryData.observe(viewLifecycleOwner){
                Log.d("TAG", "onViewCreated: ${it}")
                diaryAdapter.setData(it)

            }
        }
    }
    private fun getToken(): String {
        viewModel.readToken.asLiveData().observe(viewLifecycleOwner){
            lifecycleScope.launch {

                diaryViewModel.getDiary(it.token)

            }
        }
        return token
    }

    private fun setAdapter() {
        binding.diaryRecycler.apply {
            adapter = diaryAdapter
            layoutManager =
                GridLayoutManager(requireContext(),2, LinearLayoutManager.VERTICAL, false)
        }
    }
}