package com.jack.instagramclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.jack.instagramclone.Models.Reel
import com.jack.instagramclone.Models.post
import com.jack.instagramclone.R
import com.jack.instagramclone.adapter.MyPostRvAdapter
import com.jack.instagramclone.adapter.MyReelRvAdapter
import com.jack.instagramclone.databinding.FragmentMypostBinding


class MypostFragment : Fragment() {
private lateinit var binding:FragmentMypostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding=FragmentMypostBinding.inflate(inflater,container,false)
        var postlist=ArrayList<post>()
        var adapter= MyPostRvAdapter(requireContext(),postlist)
        binding.rv.layoutManager= StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            var templist=ArrayList<post>()
            for(i in it.documents){
                var post: post =i.toObject<post>()!!
                templist.add(post)
            }
            postlist.addAll(templist)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}