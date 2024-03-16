package com.jack.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.jack.instagramclone.Models.user
import com.jack.instagramclone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.LOGINLOGIN.setOnClickListener {
            if(binding.LoginEmail.editText?.text.toString().equals("")or
            binding.loginPassword.editText?.text.toString().equals(""))
            {
                Toast.makeText(this@LoginActivity,"please fill all the deatils",Toast.LENGTH_SHORT).show()
            }
            else
            {
                var user=user(binding.LoginEmail.editText?.text.toString(),
                    binding.loginPassword.editText?.text.toString() )
                Firebase.auth.signInWithEmailAndPassword(user.email!!,user.password!!).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                        finish()

                    }else
                    {
                        Toast.makeText(this@LoginActivity,it.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}