package com.jack.instagramclone.Models

class Reel {
    var reelURL:String=""
    var caption:String=""
    var profilelink:String?=null

    constructor()
    constructor(reelURL: String,caption:String) {
        this.reelURL = reelURL
        this.caption=caption
    }

    constructor(reelURL: String, caption: String, profilelink: String) {
        this.reelURL = reelURL
        this.caption = caption
        this.profilelink = profilelink
    }
}