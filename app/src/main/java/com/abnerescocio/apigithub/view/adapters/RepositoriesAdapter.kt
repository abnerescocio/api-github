package com.abnerescocio.apigithub.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abnerescocio.apigithub.R
import com.abnerescocio.apigithub.model.Repo
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoriesAdapter(
    private val repos: List<Repo>?
): RecyclerView.Adapter<RepositoriesAdapter.RepoVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RepoVH {
        return RepoVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false))
    }

    override fun getItemCount(): Int {
        return repos?.size ?: 0
    }

    override fun onBindViewHolder(holder: RepoVH, position: Int) {
        val repo = repos?.get(position)
        holder.itemView.repo.text = repo?.name
    }


    inner class RepoVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}