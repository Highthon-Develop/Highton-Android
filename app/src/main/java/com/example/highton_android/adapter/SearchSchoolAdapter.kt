package com.example.highton_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.highton_android.R
import com.example.highton_android.data.model.auth.request.Row
import com.example.highton_android.databinding.SearchSchoolItemBinding

class SearchSchoolAdapter(val onClickListener: RecyclerViewItemClickListener<Row>) : RecyclerView.Adapter<SearchSchoolAdapter.SearchSchoolHolder>() {

    private var searchList = mutableListOf<Row>()

    class SearchSchoolHolder(val binding: SearchSchoolItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(bind: Row) {

            binding.data= bind
            binding.executePendingBindings( )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchSchoolHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<SearchSchoolItemBinding>(
            layoutInflater,
            R.layout.search_school_item,
            parent,
            false
        )

        return SearchSchoolHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchSchoolHolder, position: Int) {
        holder.bind(searchList[position])
        holder.itemView.setOnClickListener{
            onClickListener.onclick(searchList[position])
        }

    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    fun setData(data: List<Row>) {

        val diffUtil = SearchSchoolDiffUtil(searchList, (data))
        val diffUtilResult = diffUtil.let { DiffUtil.calculateDiff(it) }
        searchList = (data).toMutableList()
        diffUtilResult.dispatchUpdatesTo(this)
    }
    class SearchSchoolDiffUtil(
        private val oldList: List<Row>,
        private val newList: List<Row>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] === newList[newItemPosition]


        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] === newList[newItemPosition]


        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            return getChangePayload(
                oldItemPosition,
                newItemPosition
            )
        }
    }
}