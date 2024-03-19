package com.jack.instagramclone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.jack.instagramclone.Models.user
import com.jack.instagramclone.R
import com.jack.instagramclone.adapter.viewpagerAdapter
import com.jack.instagramclone.databinding.FragmentProfileBinding
import com.jack.instagramclone.signupActivity
import com.jack.instagramclone.utils.USER_NODE
import com.squareup.picasso.Picasso


class profileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var  viewpagerAdapter: viewpagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(inflater, container, false)

        binding.editProfile.setOnClickListener {
            val intent= Intent(activity,signupActivity::class.java)
            intent.putExtra("MODE",1)
            activity?.startActivity(intent)
            activity?.finish()
        }
        viewpagerAdapter= viewpagerAdapter(requireActivity().supportFragmentManager)
        viewpagerAdapter.addFragments(MypostFragment(),"My Post")
        viewpagerAdapter.addFragments(MyReelsFragment(),"My Reels")
        binding.viewpager.adapter=viewpagerAdapter
        binding.tablayout.setupWithViewPager(binding.viewpager)
        return binding.root
    }

    companion object {


            }

    override fun onStart() {
        super.onStart()
               Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                   val user:user= it.toObject<user>()!!
                   binding.name.text=user.name
                   binding.bio.text=user.email
                   if(!user.image.isNullOrEmpty()){
                       Picasso.get().load(user.image).into(binding.profileImage)

                   }

               }
    }
    }
