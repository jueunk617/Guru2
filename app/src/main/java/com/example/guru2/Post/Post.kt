package com.example.guru2.Post

data class Post(
    val category: String,
    val time: String,
    val title: String,
    val content: String,
    val hashtags: List<String>,
    val participants: Int,
    val views: Int
) {
    // 인자 빈 생성자
    constructor() : this("", "", "", "", emptyList(), 0, 0)
}

