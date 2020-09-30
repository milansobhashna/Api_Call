package com.da.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_add_new_friend.view.*

class AddNewFriendAdapter(
    private val context: Context,
    private var listDate: ArrayList<Category>
) : RecyclerView.Adapter<AddNewFriendAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_add_new_friend,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listDate.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCgItemUsername.text = listDate[position].name

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCgItemUsername: TextView = view.tv_cg_item_username
    }
}