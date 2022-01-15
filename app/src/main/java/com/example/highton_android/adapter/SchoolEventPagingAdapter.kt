//package com.example.highton_android.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.paging.PagingDataAdapter
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.example.highton_android.data.model.feed.response.Content
//
//class SchoolEventPagingAdapter(
//    val onClickListener: RecyclerViewItemClickListener<Content>
//) :
//    PagingDataAdapter<Content, SchoolEventPagingAdapter.PartnerRecyclerAdapterViewHolder>(
//        diffCallback
//    ) {
//    companion object {
//
//        private val diffCallback = object : DiffUtil.ItemCallback<Content>() {
//            override fun areItemsTheSame(
//                oldItem: Content,
//                newItem: Content
//            ): Boolean {
//                return oldItem == newItem
//            }
//
//            override fun areContentsTheSame(
//                oldItem: Content,
//                newItem: Content
//            ): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): PartnerRecyclerAdapterViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//
//        val binding = DataBindingUtil.inflate<CommunityRecyclerViewItemBinding>(
//            layoutInflater,
//            R.layout.community_recycler_view_item,
//            parent,
//            false
//        )
//
//        return PartnerRecyclerAdapterViewHolder(binding)
//    }
//
//
//    override fun onBindViewHolder(holder: PartnerRecyclerAdapterViewHolder, position: Int) {
//        val item = getItem(position)
//        if (item != null) {
//            holder.bind(item)
//
//        }
//    }
//
//
//    inner class PartnerRecyclerAdapterViewHolder(val binding: CommunityRecyclerViewItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(bind: Content) {
//            binding.executePendingBindings()
//            binding.groupSetImg.setOnClickListener {
//                onClickListener.onclick(bind)
//            }
//            itemView.setOnClickListener {
//                onLayoutClickListener.onclicks(bind)
//            }
//
//        }
//    }
//
//
//}
