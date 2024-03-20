package com.jack.instagramclone.Models

class post {
    var postURL:String=""
    var caption:String=""
    var  name:String=""
    var time:String=" "

    constructor()
    constructor(postURL: String,caption:String) {
        this.postURL = postURL
        this.caption=caption
    }

    constructor(postURL: String, caption: String, name: String, time: String) {
        this.postURL = postURL
        this.caption = caption
        this.name= name
        this.time = time
    }


}