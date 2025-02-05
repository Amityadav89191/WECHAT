package com.jack.instagramclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.jack.instagramclone.Models.Reel
import com.jack.instagramclone.R
import com.jack.instagramclone.adapter.ReelAdapter
import com.jack.instagramclone.databinding.FragmentReelBinding
import com.jack.instagramclone.utils.REEL


class reelFragment : Fragment() {
private lateinit var binding: FragmentReelBinding
lateinit var adapter: ReelAdapter
var reellist=ArrayList<Reel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentReelBinding.inflate(inflater, container, false)
        adapter=ReelAdapter(requireContext(),reellist)
        binding.viewpager.adapter=adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {
            var templist=ArrayList<Reel>()
            reellist.clear()
            for (i in it.documents)
            {
                var reel=i.toObject<Reel>()!!
                templist.add(reel)

            }
            reellist.addAll(templist)
            reellist.reverse()
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    companion object {

    }
}