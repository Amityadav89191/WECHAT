package com.jack.instagramclone.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.jack.instagramclone.Models.post
import com.jack.instagramclone.Models.user
import com.jack.instagramclone.R
import com.jack.instagramclone.databinding.PostRvBinding
import com.jack.instagramclone.utils.USER_NODE

class PostAdapter(var context: Context,var postlist:ArrayList<post>) :RecyclerView.Adapter<PostAdapter.MyHolder>() {
    inner class MyHolder(var binding: PostRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var binding = PostRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)

    }

    override fun getItemCount(): Int {
        return postlist.size
    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        try {
            Firebase.firestore.collection(USER_NODE).document(postlist.get(position).name).get()
                .addOnSuccessListener {

                    var user=it.toObject<user>()
                    Glide.with(context).load(user!!.image).placeholder(R.drawable.user).into(holder.binding.profileImage)
                    holder.binding.name.text=user.name


                }
        }catch (e:Exception){


        }
        Glide.with(context).load(postlist.get(position).postURL).placeholder(R.drawable.loading).into(holder.binding.homePostImage)
     //   val text: String =TimeAgo.using(postlist.get(position).time.toLong())
        holder.binding.time.text=postlist.get(position).time
        holder.binding.homeShare.setOnClickListener {
            var i=Intent(Intent.ACTION_SEND)
            i.type="text/plain"
            i.putExtra(Intent.EXTRA_TEXT,postlist.get(position).postURL)
            context.startActivities(arrayOf(i))
        }
        holder.binding.homeCapationPost.text=postlist.get(position).caption
        holder.binding.homeLike.setOnClickListener {
            holder.binding.homeLike.setImageResource(R.drawable.like_button_1)
        }


    }

}
