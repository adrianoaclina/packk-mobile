package br.com.packkmobile.home.presentation.post

import br.com.packkmobile.home.domain.entity.Comment

sealed class PostsEvent {
    data class OpenComments(val postId: Int) : PostsEvent()
    data class InsertComment(
        val comment: Comment
    ) : PostsEvent()
}
