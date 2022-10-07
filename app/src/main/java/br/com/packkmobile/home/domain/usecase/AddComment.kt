package br.com.packkmobile.home.domain.usecase

import br.com.packkmobile.home.domain.entity.Comment
import br.com.packkmobile.home.domain.entity.CommentEntity
import br.com.packkmobile.home.domain.repository.CommentRepository
import br.com.packkmobile.network.entity.Result

class AddComment(
    private val repository: CommentRepository
) {

    suspend operator fun invoke(comment: Comment): Result<Unit> {
        return repository.insertComment(
            CommentEntity(
                postId = comment.postId,
                body = comment.body,
                name = comment.name,
                email = comment.email
            )
        )
    }
}