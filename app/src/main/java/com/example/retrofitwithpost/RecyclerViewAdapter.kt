package com.example.retrofitwithpost

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitwithpost.databinding.RecyclerRowListBinding

class RecyclerViewAdapter(val clickListener: OnItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

   var userList = mutableListOf<UserItem>()
    class MyViewHolder(val binding: RecyclerRowListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRowListBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(userList[position])
        val item = userList[position]

        Log.d("@@@", "$item")

        with(holder.binding) {
            tvName.text = item.type
            tvEmail.text = item.comment
        }

        holder.itemView.setOnClickListener{
            clickListener.onItemEditClick(item)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    interface OnItemClickListener {
        fun onItemEditClick(user: UserItem)
    }

}