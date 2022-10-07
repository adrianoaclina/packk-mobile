package br.com.packkmobile.home.domain.entity

data class Comment(
    val postId: Int,
    val id: Int? = null,
    val name: String,
    val email: String,
    val body: String
)
