package com.jack.instagramclone.utils
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID
import java.util.concurrent.TimeUnit

fun uploadImage(uri: Uri,Foldername:String,callback:(String?)->Unit){
    var imageurl:String?=null
    FirebaseStorage.getInstance().getReference(Foldername).child(UUID.randomUUID().toString()).putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageurl=it.toString()
                callback( imageurl)
            }
        }


}