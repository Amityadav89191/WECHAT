package com.jack.instagramclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jack.instagramclone.Models.user
import com.jack.instagramclone.R
import com.jack.instagramclone.databinding.FollowRvBinding

class FollowAdapter(var context: Context,var followlist:ArrayList<user>):RecyclerView.Adapter<FollowAdapter.ViewHolder>(){
    inner class ViewHolder(var bindind:FollowRvBinding):RecyclerView.ViewHolder(bindind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         var bindind=FollowRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(bindind)
    }

    override fun getItemCount(): Int {
        return followlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         Glide.with(context).load(followlist.get(position).image).placeholder(R.drawable.user).into(
             holder.bindind.profileStatus)
        holder.bindind.name.text=followlist.get(position).name


    }
}