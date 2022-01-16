package com.example.highton_android.view.auth.up

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.databinding.FragmentAuthSchoolSearchFinishBinding
import com.example.highton_android.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchSchoolFinishFragment :
    BaseFragment<FragmentAuthSchoolSearchFinishBinding>(R.layout.fragment_auth_school_search_finish) {

    private val viewModel: AuthViewModel by activityViewModels()
    override fun FragmentAuthSchoolSearchFinishBinding.onCreateView() {

        binding.schoolNameText.text = viewModel.schoolName.value.toString()
    }

    override fun FragmentAuthSchoolSearchFinishBinding.onViewCreated() {
        binding.accountCreateBtn.setOnClickListener {
            findNavController().navigate(R.id.action_searchSchoolFinishFragment_to_authStudentInFormationFragment)
        }
    }
}