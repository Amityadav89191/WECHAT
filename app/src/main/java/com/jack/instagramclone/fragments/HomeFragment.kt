package com.jack.instagramclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.jack.instagramclone.Models.post
import com.jack.instagramclone.R
import com.jack.instagramclone.adapter.PostAdapter
import com.jack.instagramclone.databinding.FragmentHome2Binding
import com.jack.instagramclone.utils.POST


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHome2Binding
    private var postlist=ArrayList<post>()
    private lateinit var adapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHome2Binding.inflate(inflater, container, false)
        adapter= PostAdapter(requireContext(),postlist)
        binding.postRv.layoutManager=LinearLayoutManager(requireContext())
        binding.postRv.adapter=adapter
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar2)
        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            var templist=ArrayList<post>()
            postlist.clear()
            for(i in it.documents){
                var post:post=i.toObject<post>()!!
                templist.add(post)
            }
            postlist.addAll(templist)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu,menu)


         super.onCreateOptionsMenu(menu, inflater)
    }
}