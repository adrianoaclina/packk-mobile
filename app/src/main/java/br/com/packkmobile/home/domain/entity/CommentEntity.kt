package br.com.packkmobile.home.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
){
    fun transform() = Comment(
        id = id ?: 0,
        postId = postId ,
        name = name,
        email = email,
        body = body
    )
}
