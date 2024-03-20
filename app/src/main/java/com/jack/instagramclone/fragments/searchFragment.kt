package com.jack.instagramclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.app
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.jack.instagramclone.Models.user
import com.jack.instagramclone.R
import com.jack.instagramclone.adapter.SerachAdapter
import com.jack.instagramclone.databinding.FragmentSearchBinding
import com.jack.instagramclone.utils.USER_NODE


class searchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var adapter: SerachAdapter
     var userlist = ArrayList<user>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = SerachAdapter(requireContext(), userlist)
        binding.rv.adapter = adapter


        Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {
            var templist = ArrayList<user>()
            userlist.clear()
            for (i in it.documents) {
                if (i.id.toString().equals(Firebase.auth.currentUser!!.uid.toString())) {

                } else {
                    var user: user = i.toObject<user>()!!
                    templist.add(user)

                }
            }
            userlist.addAll(templist)
            adapter.notifyDataSetChanged()
        }
        binding.searchButton.setOnClickListener {
            var text=binding.searchView.text.toString()
            Firebase.firestore.collection(USER_NODE).whereEqualTo("name",text).get().addOnSuccessListener {

                var templist = ArrayList<user>()
                userlist.clear()
                if (it.isEmpty) {

                } else {
                    for (i in it.documents) {
                        if (i.id.toString().equals(Firebase.auth.currentUser!!.uid.toString())) {

                        } else {
                            var user: user = i.toObject<user>()!!
                            templist.add(user)

                        }
                    }
                    userlist.addAll(templist)
                    adapter.notifyDataSetChanged()

                }
            }

        }
        return binding.root
    }

    companion object {
    }
}