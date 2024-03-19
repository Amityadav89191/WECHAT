package com.jack.instagramclone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jack.instagramclone.Post.PostActivity
import com.jack.instagramclone.Post.ReelsActivity
import com.jack.instagramclone.R
import com.jack.instagramclone.databinding.FragmentAddBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class addFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentAddBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding=FragmentAddBinding.inflate(inflater, container, false)

        binding.post.setOnClickListener {
          activity?.startActivity(Intent(requireContext(),PostActivity::class.java))
            activity?.finish()

        }
        binding.reel.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),ReelsActivity::class.java))


        }

        return binding.root
    }

    companion object {

    }
}