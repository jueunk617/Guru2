package com.example.guru2.data

import java.io.Serializable

data class PostDataModel (
    var type : PostType = PostType.Delivery,
    var id: String = "",
    var title: String = "",
    var content: String = "",
    var password: String = "",
    var replies: ArrayList<Replies> = ArrayList(),
    var pictures: ArrayList<String> = ArrayList()

): Serializable



enum class PostType {
    Delivery,
    Purchase
}