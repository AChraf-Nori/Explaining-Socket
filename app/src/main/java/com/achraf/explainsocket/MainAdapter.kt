package com.achraf.explainsocket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achraf.explainsocket.databinding.ChatRowToBinding

class MainAdapter(): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var myList =  arrayListOf<Message>()

    inner class ViewHolder(val binding: ChatRowToBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ChatRowToBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tvUsername.text = myList[position].myUsername
        holder.binding.tvMessage.text = myList[position].message

    }

    override fun getItemCount(): Int {
        return myList.size
    }


    fun setMessage(message: Message) {
        this.myList.add(message)
        notifyDataSetChanged()
    }


}