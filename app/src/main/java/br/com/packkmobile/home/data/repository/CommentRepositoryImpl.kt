package br.com.packkmobile.home.data.repository

import br.com.packkmobile.home.data.datasource.CommentDao
import br.com.packkmobile.home.data.service.CommentService
import br.com.packkmobile.home.domain.entity.CommentBody
import br.com.packkmobile.home.domain.entity.CommentEntity
import br.com.packkmobile.home.domain.repository.CommentRepository
import br.com.packkmobile.network.entity.Result
import br.com.packkmobile.network.executor.SafeRequest
import kotlinx.coroutines.flow.Flow

class CommentRepositoryImpl(
    private val dao: CommentDao,
    private val service: CommentService,
    private val safeRequest: SafeRequest
) : CommentRepository {

    override fun getCommentsByPostIdDao(postId: Int): List<CommentEntity> {
        return dao.getCommentByPostId(postId)
    }

    override suspend fun getCommentsByPostIdService(postId: Int) = safeRequest {
        service.getPosts(postId)
    }

    override suspend fun insertComment(commentEntity: CommentEntity) = safeRequest {
        dao.insertComment(commentEntity)
        return@safeRequest service.postComments(
            comment = CommentBody(
                commentEntity.name,
                commentEntity.email,
                commentEntity.body
            )
        )
    }

}