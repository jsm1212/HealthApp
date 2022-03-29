package com.example.healthapp.bbs

class BbsReplyDto(
    var seq:Int, val replyNum:Int, val id:String, val nickname:String, val title:String, val content:String, val wdate:String,
    val ref:Int, val step:Int, val depth:Int, val del:Int, val readCount:Int, val replyLike:Int, val replyImage:String) {
}