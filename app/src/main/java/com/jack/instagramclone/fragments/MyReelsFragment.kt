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
import com.jack.instagramclone.databinding.FragmentMyReelsBinding
import com.jack.instagramclone.databinding.FragmentMypostBinding
import com.jack.instagramclone.databinding.FragmentReelBinding
import com.jack.instagramclone.utils.REEL


class MyReelsFragment : Fragment() {
    private lateinit var binding:FragmentMyReelsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMyReelsBinding.inflate(inflater, container, false)
        var reellist=ArrayList<Reel>()
        var adapter= MyReelRvAdapter(requireContext(), reellist)
        binding.rv.layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ REEL).get().addOnSuccessListener {
            var templist=ArrayList<Reel>()
            for(i in it.documents){
                var reel: Reel =i.toObject<Reel>()!!
                templist.add(reel)
            }
            reellist.addAll(templist)
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    companion object {



    }
}