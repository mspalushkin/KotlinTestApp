/*
 * 26.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.palushkin.kotlintestapp.databinding.ListItemBinding
import com.palushkin.kotlintestapp.domain.DomainUser

class UserListAdapter(val onClickListener: OnClickListener) :
        ListAdapter<DomainUser, UserListAdapter.UserViewHolder>(DiffCallback) {


    class UserViewHolder(private var binding: ListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DomainUser) {
            binding.property = user
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<DomainUser>() {
        override fun areItemsTheSame(oldItem: DomainUser, newItem: DomainUser): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DomainUser, newItem: DomainUser): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): UserViewHolder {
        return UserViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(user)
        }
        holder.bind(user)
    }


    class OnClickListener(val clickListener: (user: DomainUser) -> Unit) {
        fun onClick(user: DomainUser) = clickListener(user)
    }


}