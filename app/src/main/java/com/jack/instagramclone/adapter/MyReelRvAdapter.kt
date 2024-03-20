package com.jack.instagramclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jack.instagramclone.Models.Reel
import com.jack.instagramclone.Models.post
import com.jack.instagramclone.databinding.MyPostRvDesignBinding
import com.squareup.picasso.Picasso

class MyReelRvAdapter(var context:Context,var reelLIST:ArrayList<Reel>):RecyclerView.Adapter<MyReelRvAdapter.ViewHolder>(){
    inner class ViewHolder(var binding:MyPostRvDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=MyPostRvDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  reelLIST.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(reelLIST.get(position).reelURL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.postImage)
    }
}