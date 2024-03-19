package com.jack.instagramclone.utils

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID
import java.util.concurrent.TimeUnit

fun uploadImage(uri: Uri, Foldername: String, callback: (String?) -> Unit) {
    var imageurl: String? = null
    FirebaseStorage.getInstance().getReference(Foldername).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageurl = it.toString()
                callback(imageurl)
            }
        }


}

fun uploadvideo(
    uri: Uri,
    Foldername: String,
    progressDialog: ProgressDialog,
    callback: (String?) -> Unit
) {
    var imageurl: String? = null
    progressDialog.setTitle("Ruko jara sabar karo. . . ")
    progressDialog.show()
    FirebaseStorage.getInstance().getReference(Foldername).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageurl = it.toString()
                progressDialog.dismiss()
                callback(imageurl)
            }
        }
        .addOnProgressListener {
            var uploadedvalue: Long = (it.bytesTransferred / it.totalByteCount)*100
            progressDialog.setMessage("uploaded $uploadedvalue %")

        }


}