package com.abnerescocio.apigithub.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abnerescocio.apigithub.R
import com.abnerescocio.apigithub.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(
    private val users: List<User>?
): RecyclerView.Adapter<UsersAdapter.UsersVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVH {
        return UsersVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return users?.size ?: 0
    }

    override fun onBindViewHolder(holder: UsersVH, position: Int) {
        val user = users?.get(position)
        holder.itemView.name.text = user?.name
        if (user != null && user.isAdmin) {
            holder.itemView.description.visibility = View.VISIBLE
            holder.itemView.description.text = holder.itemView.context.getString(R.string.administrator)
        } else {
            holder.itemView.description.visibility = View.GONE
        }

        Glide.with(holder.itemView).load(user?.avatarUrl).into(holder.itemView.avatar)
    }

    inner class UsersVH(itemView: View): RecyclerView.ViewHolder(itemView)
}