package com.example.proyek_mad.ui.askgemini

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.proyek_mad.data.Chat
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.databinding.ItemChatCardBinding
import com.example.proyek_mad.databinding.ItemModuleCardBinding

class GeminiDiffUtil: DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem.chat_id == newItem.chat_id
    }

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }

}

val geminiDiffUtil = GeminiDiffUtil()

class GeminiAdapter: ListAdapter<Chat, GeminiAdapter.ViewHolder>(geminiDiffUtil) {

    class ViewHolder(val binding: ItemChatCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChatCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = getItem(position)

        if(chat.from == 1) {
            holder.binding.txtGeminiMessage.text = chat.message
            holder.binding.txtGeminiMessage.visibility = View.VISIBLE
            holder.binding.txtUserMessage.visibility = View.GONE
        } else {
            holder.binding.txtUserMessage.text = chat.message
            holder.binding.txtUserMessage.visibility = View.VISIBLE
            holder.binding.txtGeminiMessage.visibility = View.GONE
        }

    }
}