package com.example.highton_android.view.main.meal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.databinding.FragmentMealBinding
import com.example.highton_android.utils.NetworkResult
import com.example.highton_android.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {
    private val mealViewModel: MealViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun FragmentMealBinding.onCreateView() {
        observeData()

        binding.buttonClose.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    fun observeData() {
        mealViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
            mealViewModel.getUser(it.token)
        }

        mealViewModel.user.observe(viewLifecycleOwner) {
            mealViewModel.getMeal()
        }

        mealViewModel.mealResult.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    setMealData(response.data!!)
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        })
    }

    private fun setMealData(data: String) {
        binding.textMeal.text = data
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setMenuVisibility(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).setMenuVisibility(true)
    }
}