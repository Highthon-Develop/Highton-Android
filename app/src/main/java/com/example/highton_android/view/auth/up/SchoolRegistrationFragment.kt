package com.example.highton_android.view.auth.up

import androidx.navigation.fragment.findNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.databinding.FargmentAuthSchoolRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolRegistrationFragment :
    BaseFragment<FargmentAuthSchoolRegistrationBinding>(R.layout.fargment_auth_school_registration) {
    override fun FargmentAuthSchoolRegistrationBinding.onCreateView() {

    }

    override fun FargmentAuthSchoolRegistrationBinding.onViewCreated() {
        with(binding) {
            searchSchoolLayout.setOnClickListener {
                findNavController().navigate(R.id.action_schoolRegistrationFragment_to_searchSchoolFragment)
            }
            inviteCodeLayout.setOnClickListener {
                //todo
            }
        }
    }
}