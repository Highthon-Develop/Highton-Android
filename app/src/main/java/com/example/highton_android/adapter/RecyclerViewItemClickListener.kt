package com.example.highton_android.adapter

interface RecyclerViewItemClickListener<T> {
    fun onclick(data: T): Unit
}