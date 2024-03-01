package com.example.lovecalculator.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lovecalculator.databinding.ItemRvHistoryBinding
import com.example.lovecalculator.remote.LoveModel

class HistoryRVAdapter(
    private val onLongItemClick: (loveModel: LoveModel) -> Unit
) : ListAdapter<LoveModel, ItemHistoryViewHolder>(DIFF_UTIL_CALL_BACK) {

    private companion object {
        val DIFF_UTIL_CALL_BACK: DiffUtil.ItemCallback<LoveModel> =
            object : DiffUtil.ItemCallback<LoveModel>() {
                override fun areItemsTheSame(
                    oldItem: LoveModel, newItem: LoveModel
                ): Boolean {
                    return oldItem.uid == newItem.uid
                }

                override fun areContentsTheSame(
                    oldItem: LoveModel, newItem: LoveModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHistoryViewHolder {
        return ItemHistoryViewHolder(
            ItemRvHistoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnLongClickListener {
            onLongItemClick(getItem(position))
            true
        }
    }
}

class ItemHistoryViewHolder(private val binding: ItemRvHistoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(loveModel: LoveModel) {
        loveModel.apply {
            binding.apply {
                tvFirstName.text = firstName
                tvSecondName.text = secondName
                val percent = "$percentage%"
                tvPercentage.text = percent
            }
        }
    }
}