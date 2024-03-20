package com.jack.instagramclone

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.jack.instagramclone.Models.user
import com.jack.instagramclone.databinding.ActivitySignupBinding
import com.jack.instagramclone.utils.USER_NODE
import com.jack.instagramclone.utils.USER_PROFILE_FOLDER
import com.jack.instagramclone.utils.uploadImage
import com.squareup.picasso.Picasso


class signupActivity : AppCompatActivity() {
    val binding by lazy{
        ActivitySignupBinding.inflate(layoutInflater)
    }
       lateinit var user:user
       private val launcher=registerForActivityResult(ActivityResultContracts.GetContent()){
           uri->
           uri?.let {
               uploadImage(uri, USER_PROFILE_FOLDER){
                   if(it!=null){
                       user.image=it
                       binding.profileImage.setImageURI(uri)
                   }
               }
           }
       }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val text = "<font color=#FF000000> Already Have an Account</font> <font color=#1E88E5>Login?</font>"
        binding.txtLogin.setText(Html.fromHtml(text))

        user=user()
        if(intent.hasExtra("MODE")) {
            if (intent.getIntExtra("MODE", -1) == 1) {
                binding.signUpButton
                    .text = "Update Profile"
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid)
                    .get().addOnSuccessListener {
                    user = it.toObject<user>()!!
                        if(user.image.isNullOrEmpty()){
                            Picasso.get().load(user.image).into(binding.profileImage)

                        }
                        binding.name.editText?.setText(user.name)
                        binding.email.editText?.setText(user.email)
                        binding.password.editText?.setText(user.password)

                }
            }
        }
        binding.signUpButton.setOnClickListener {
            if(intent.hasExtra("MODE")) {
                if (intent.getIntExtra("MODE", -1) == 1) {
                    Firebase.firestore.collection(USER_NODE)
                        .document(Firebase.auth.currentUser!!.uid).set(user)
                        .addOnSuccessListener {
                            startActivity(
                                Intent(
                                    this@signupActivity,
                                    HomeActivity::class.java
                                )
                            )
                            finish()
                        }

                }
            }
            else {

                if (binding.name.editText?.text.toString().equals("") or
                    binding.email.editText?.text.toString().equals("") or
                    binding.password.editText?.text.toString().equals("")
                ) {
                    Toast.makeText(
                        this@signupActivity,
                        "please fill the all information",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.email.editText?.text.toString(),
                        binding.password.editText?.text.toString()
                    ).addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            Toast.makeText(
                                this@signupActivity,
                                "Login succesful",
                                Toast.LENGTH_SHORT
                            ).show()
                            user.name = binding.name.editText?.text.toString()
                            user.password = binding.password.editText?.text.toString()
                            user.email = binding.email.editText?.text.toString()
                            Firebase.firestore.collection(USER_NODE)
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    startActivity(
                                        Intent(
                                            this@signupActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }

                        } else {
                            Toast.makeText(
                                this@signupActivity,
                                result.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }

        }
        binding.profileImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this@signupActivity,LoginActivity::class.java))
            finish()
        }
    }
}