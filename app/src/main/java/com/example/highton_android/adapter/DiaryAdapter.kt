package com.example.highton_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.highton_android.R
import com.example.highton_android.databinding.ItemDiaryItemBinding

class DiaryAdapter : RecyclerView.Adapter<DiaryAdapter.DiaryHolder>() {


    private var diaryList = mutableListOf<String>()

    class DiaryHolder(val binding: ItemDiaryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bind: String) {

            binding.data = bind
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ItemDiaryItemBinding>(
            layoutInflater,
            R.layout.item_diary_item,
            parent,
            false
        )

        return DiaryHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaryHolder, position: Int) {
        holder.bind(diaryList[position])
    }
    fun setData(data:List<String>) {

        val diffUtil = SearchSchoolDiffUtil(diaryList, (data))
        val diffUtilResult = diffUtil.let { DiffUtil.calculateDiff(it) }
        diaryList = (data).toMutableList()
        diffUtilResult.dispatchUpdatesTo(this)
    }
    class SearchSchoolDiffUtil(
        private val oldList: List<String>,
        private val newList: List<String>
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