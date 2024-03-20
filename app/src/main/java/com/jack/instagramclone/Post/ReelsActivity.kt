package com.jack.instagramclone.Post

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.jack.instagramclone.HomeActivity
import com.jack.instagramclone.Models.Reel
import com.jack.instagramclone.Models.post
import com.jack.instagramclone.Models.user
import com.jack.instagramclone.R
import com.jack.instagramclone.databinding.ActivityReelsBinding
import com.jack.instagramclone.utils.POST
import com.jack.instagramclone.utils.POST_FOLDER
import com.jack.instagramclone.utils.REEL
import com.jack.instagramclone.utils.Reel_FOLDER
import com.jack.instagramclone.utils.USER_NODE
import com.jack.instagramclone.utils.uploadImage
import com.jack.instagramclone.utils.uploadvideo

class ReelsActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityReelsBinding.inflate(layoutInflater)
    }
    private lateinit var videourl: String
    lateinit var progressdialog:ProgressDialog
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadvideo(uri, Reel_FOLDER,progressdialog)
            { url ->
                if (url != null) {

                    videourl = url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressdialog=ProgressDialog(this)
        binding.selectReel.setOnClickListener {
            launcher.launch("video/*")
        }
        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }

        binding.postButton.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user:user=it.toObject<user>()!!

                val reel: Reel = Reel(videourl!!, binding.caption.editText?.text.toString(),user.image!!)
                Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).document()
                        .set(reel).addOnSuccessListener {
                            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
                            finish()
                        }
            }


            }
        }
    }
}