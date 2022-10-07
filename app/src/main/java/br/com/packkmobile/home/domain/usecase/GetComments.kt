package br.com.packkmobile.home.domain.usecase

import br.com.packkmobile.home.domain.entity.Comment
import br.com.packkmobile.home.domain.repository.CommentRepository
import br.com.packkmobile.network.entity.onSuccess
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class GetComments(
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(postId: Int): List<Comment> {
        val comments: ArrayList<Comment> = ArrayList()
        val result = commentRepository.getCommentsByPostIdService(postId)
        result.onSuccess { listComments ->
            listComments.map {
                comments.add(it)
            }
        }
        commentRepository.getCommentsByPostIdDao(postId).map {
            comments.add(it.transform())
        }

        return comments
    }

}