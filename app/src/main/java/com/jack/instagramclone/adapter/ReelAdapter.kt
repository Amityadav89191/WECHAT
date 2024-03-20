package com.jack.instagramclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jack.instagramclone.Models.Reel
import com.jack.instagramclone.R
import com.jack.instagramclone.databinding.ReelDgBinding
import com.squareup.picasso.Picasso

class ReelAdapter(var context:Context,var reellist:ArrayList<Reel>):RecyclerView.Adapter<ReelAdapter.ViewHolder> (){
    inner class ViewHolder(var binding: ReelDgBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         var binding=ReelDgBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return reellist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(reellist.get(position).profilelink).placeholder(R.drawable.user).into(holder.binding.profileImage)
        holder.binding.caption.setText(reellist.get(position).caption)
        holder.binding.videoView2.setVideoPath(reellist.get(position
        ).reelURL)
        holder.binding.videoView2.setOnPreparedListener {
            holder.binding.progressBar3.visibility=View.GONE
            holder.binding.videoView2.start()
        }
    }

}