package com.example.highton_android.view.auth.up

import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.data.model.auth.request.AccountRequest
import com.example.highton_android.databinding.FragmentAuthStudentInformationBinding
import com.example.highton_android.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthStudentInFormationFragment :
    BaseFragment<FragmentAuthStudentInformationBinding>(R.layout.fragment_auth_student_information) {
    private var sexText = "Male"
    private var setGrade = 1
    private val viewModel: AuthViewModel by activityViewModels()
    override fun FragmentAuthStudentInformationBinding.onCreateView() {
        binding.schoolNameText.text = viewModel.schoolName.value.toString()
        Log.d("TAG", "onCreateView: ${viewModel.scCode.value} ")

    }

    override fun FragmentAuthStudentInformationBinding.onViewCreated() {
        setSex()
        setGrade()
        postAccount()
        with(viewModel) {
            isSuccess.observe(viewLifecycleOwner) {
                if (it)
                    findNavController().navigate(R.id.action_authStudentInFormationFragment_to_loginFragment)else
                    Toast.makeText(requireContext(),"실패",Toast.LENGTH_SHORT).show()

            }
            isFailure.observe(viewLifecycleOwner){
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun postAccount() {
        binding.accountCreateBtn.setOnClickListener {

            lifecycleScope.launch {
                viewModel.postAccount(
                    AccountRequest(
                        binding.emailEditText.text.toString(),
                        binding.passwordEditText.text.toString(),
                        binding.nameText.text.toString(),
                        binding.birthYearEditText.text.toString().toInt(),
                        binding.birthMonthEditText.text.toString().toInt(),
                        binding.birthDayEditText.text.toString().toInt(),
                        viewModel.scCode.value.toString(),
                        viewModel.schulCode.value.toString(),
                        setGrade,
                        sexText,
                        binding.nicknameEditText.text.toString()
                    )
                )

            }
        }
    }


    private fun setSex() {

        binding.mainText.setOnClickListener {
            sexText = "Male"
            binding.mainText.setBackgroundResource(R.drawable.item_bg_on);
            binding.mainText.setTextColor(Color.WHITE);
            binding.girlText.setBackgroundResource(R.drawable.item_bg_outline);
            binding.girlText.setTextColor(Color.GRAY);
        }
        binding.girlText.setOnClickListener {
            sexText = "Female"
            binding.girlText.setBackgroundResource(R.drawable.item_bg_on);
            binding.girlText.setTextColor(Color.WHITE);
            binding.mainText.setBackgroundResource(R.drawable.item_bg_outline);
            binding.mainText.setTextColor(Color.GRAY);
        }
    }

    private fun setGrade() {
        binding.firstGradeText.setOnClickListener {
            setGrade = 1
            binding.gradeText.text = "1학년"
            binding.firstGradeText.setBackgroundResource(R.drawable.item_bg_on);
            binding.firstGradeText.setTextColor(Color.WHITE);
            binding.secondGradeText.setBackgroundResource(R.drawable.item_bg_outline);
            binding.secondGradeText.setTextColor(Color.GRAY);

            binding.thirdGradeText.setBackgroundResource(R.drawable.item_bg_outline)
            binding.thirdGradeText.setTextColor(Color.GRAY);

        }
        binding.secondGradeText.setOnClickListener {
            setGrade = 2
            binding.gradeText.text = "2학년"

            binding.firstGradeText.setBackgroundResource(R.drawable.item_bg_outline);
            binding.firstGradeText.setTextColor(Color.GRAY);
            binding.secondGradeText.setBackgroundResource(R.drawable.item_bg_on);
            binding.secondGradeText.setTextColor(Color.WHITE);
            binding.thirdGradeText.setBackgroundResource(R.drawable.item_bg_outline);
            binding.thirdGradeText.setTextColor(Color.GRAY);
        }
        binding.thirdGradeText.setOnClickListener {
            setGrade = 3
            binding.gradeText.text = "3학년"

            binding.firstGradeText.setBackgroundResource(R.drawable.item_bg_outline);
            binding.firstGradeText.setTextColor(Color.GRAY);
            binding.secondGradeText.setBackgroundResource(R.drawable.item_bg_outline);
            binding.secondGradeText.setTextColor(Color.GRAY);
            binding.thirdGradeText.setBackgroundResource(R.drawable.item_bg_on);
            binding.thirdGradeText.setTextColor(Color.WHITE);

        }
    }

    private fun dataNullTest() =
        binding.inforCheckbox.isChecked && !(TextUtils.isEmpty(binding.birthYearEditText.text.toString()))
                && !(TextUtils.isEmpty(binding.birthMonthEditText.text.toString())) && !(TextUtils.isEmpty(
            binding.birthDayEditText.text.toString()
        ))
                && !(TextUtils.isEmpty(binding.emailEditText.text.toString())) && !(TextUtils.isEmpty(
            binding.nicknameEditText.text.toString()
        ))


}