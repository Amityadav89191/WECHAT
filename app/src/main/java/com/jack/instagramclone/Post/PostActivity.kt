package com.jack.instagramclone.Post


import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.jack.instagramclone.HomeActivity
import com.jack.instagramclone.Models.post
import com.jack.instagramclone.Models.user

import com.jack.instagramclone.databinding.ActivityPostBinding
import com.jack.instagramclone.utils.POST
import com.jack.instagramclone.utils.POST_FOLDER
import com.jack.instagramclone.utils.USER_NODE
import com.jack.instagramclone.utils.USER_PROFILE_FOLDER
import com.jack.instagramclone.utils.uploadImage


class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageurl: String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER)
            { url ->
                if (url != null) {
                    binding.selectImage.setImageURI(uri)
                    imageurl = url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
            finish()
            finish()
        }
        binding.selectImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
            finish()
        }
        binding.postButton.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user = it.toObject<user>()!!
                val post:post=post(
                    postURL = imageurl!!,
                    caption= binding.caption.editText?.text.toString(),
                    name=Firebase.auth.currentUser!!.uid,
                    time=System.currentTimeMillis().toString()

                )


                    Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document()
                            .set(post)
                            .addOnSuccessListener {
                                startActivity(Intent(this@PostActivity, HomeActivity::class.java))
                                finish()
                            }

                    }
                }
        }
    }
}
