package com.example.highton_android.view.auth.`in`

import android.text.TextUtils
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.data.model.auth.request.LoginRequest
import com.example.highton_android.databinding.FragmentAuthLoginBinding
import com.example.highton_android.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentAuthLoginBinding>(R.layout.fragment_auth_login) {

    private val viewModel: AuthViewModel by viewModels()


    override fun FragmentAuthLoginBinding.onCreateView() {


    }

    override fun FragmentAuthLoginBinding.onViewCreated() {
        with(viewModel) {
            binding.button.setOnClickListener {
                lifecycleScope.launch {
                    if (textNullCheck()) {
                        postLogin(
                            LoginRequest(
                                binding.idEditText.text.toString(),
                                binding.passwordEditText.text.toString()
                            )
                        )
                    } else {
                        Toast.makeText(requireContext(), "빈칸을 입력해 주세요!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            binding.signupBtn.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_schoolRegistrationFragment)
            }
            isSuccess.observe(viewLifecycleOwner) {
                if (it) {
                    //TODO
                } else {
                    Toast.makeText(requireContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            isFailure.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun textNullCheck(): Boolean {

        return !(TextUtils.isEmpty((binding.idEditText.text.toString()))) && !(TextUtils.isEmpty((binding.passwordEditText.text.toString())))

    }
}