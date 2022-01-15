package com.example.highton_android.view.auth.up

import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.highton_android.R
import com.example.highton_android.adapter.RecyclerViewItemClickListener
import com.example.highton_android.adapter.SearchSchoolAdapter
import com.example.highton_android.base.BaseFragment
import com.example.highton_android.data.model.auth.request.Row
import com.example.highton_android.databinding.FragmentAuthSchoolSearchBinding
import com.example.highton_android.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchSchoolFragment :
    BaseFragment<FragmentAuthSchoolSearchBinding>(R.layout.fragment_auth_school_search),
    RecyclerViewItemClickListener<Row> {

    private val searchSchoolAdapter: SearchSchoolAdapter by lazy {
        SearchSchoolAdapter(this)
    }

    private val viewModel: AuthViewModel by activityViewModels()
    override fun FragmentAuthSchoolSearchBinding.onCreateView() {
        setAdapter()
    }

    override fun FragmentAuthSchoolSearchBinding.onViewCreated() {

        with(viewModel) {
            with(binding) {

                searchEdit.addTextChangedListener(object:TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        lifecycleScope.launch {
                            searchSchool(searchEdit.text.toString())
                        }
                    }

                    override fun afterTextChanged(p0: Editable?) {
                    }

                })
                schoolSearch.observe(viewLifecycleOwner) {
                    if (it != null) {
                        searchSchoolAdapter.setData((it))

                    }
                }

            }
        }
    }

    private fun setAdapter() {
        binding.searchRecycler.adapter = searchSchoolAdapter
        binding.searchRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onclick(data: Row) {
        viewModel.getSchoolName(data.sCHULNM.toString(),data.aTPTOFCDCSCCODE.toString(),data.sDSCHULCODE.toString())
        findNavController().navigate(R.id.action_searchSchoolFragment_to_searchSchoolFinishFragment)
    }


}