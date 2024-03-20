package com.jack.instagramclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.jack.instagramclone.Models.user
import com.jack.instagramclone.R
import com.jack.instagramclone.databinding.SearchRvBinding
import com.jack.instagramclone.utils.FOLLOW

class SerachAdapter(var context: Context,var userlist:ArrayList<user>):RecyclerView.Adapter<SerachAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: SearchRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var binding=SearchRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isfollow=false
        Glide.with(context).load(userlist.get(position).image).placeholder(R.drawable.user).into(holder.binding.profileImage)
        holder.binding.searchName.text=userlist.get(position).name
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).whereEqualTo("email",userlist.get(position).email)
            .get().addOnSuccessListener {
                if(it.documents.size==0)
                {
                    isfollow=false
                }
                else
                {
                    holder.binding.searchFollow.text="Unfollow"
                    isfollow=true
                }
            }
        holder.binding.searchFollow.setOnClickListener {
            if (isfollow) {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).whereEqualTo("email",userlist.get(position).email)
                    .get().addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).document(it.documents.get(0).id).delete()
                        holder.binding.searchFollow.text="Follow "
                        isfollow=false

                    }
            } else {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW).document()
                    .set(
                        userlist.get(
                            position
                        )
                    )
                holder.binding.searchFollow.text = "Unfollow"
                isfollow=true

            }
        }

    }
}