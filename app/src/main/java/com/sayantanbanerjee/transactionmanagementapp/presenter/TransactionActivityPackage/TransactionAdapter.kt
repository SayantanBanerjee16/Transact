package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.databinding.ItemTransactionBinding
import java.text.SimpleDateFormat
import java.util.*

// Adapter code to display all the transaction items in the recycler view in [TransactionActivity.kt].
class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class TransactionViewHolder(
        private val binding: ItemTransactionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(record: Record) {
            binding.receiverPhone.text = record.receiverContactNumber
            binding.amount.text = "Rs. " + record.amountInvolved.toString()
            if (record.parity == 1) {
                binding.parity.text = "YOU SENT MONEY"
            } else {
                binding.parity.text = "YOU RECEIVED MONEY"
            }
            binding.status.text = record.status
            val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, hh.mm aa")
            val dateTime = simpleDateFormat.format(record.timestamp.toLong())
            binding.timestamp.text = dateTime
            val rnd = Random()
            val currentColor: Int =
                Color.argb(
                    255,
                    rnd.nextInt(50) + 200,
                    rnd.nextInt(50) + 200,
                    rnd.nextInt(50) + 200
                )
            binding.cardView.setCardBackgroundColor(currentColor)
        }
    }

}
