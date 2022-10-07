package br.com.packkmobile.home.domain.repository

import br.com.packkmobile.home.domain.entity.Comment
import br.com.packkmobile.home.domain.entity.CommentEntity
import br.com.packkmobile.network.entity.Result
import kotlinx.coroutines.flow.Flow

interface CommentRepository {

    fun getCommentsByPostIdDao(postId: Int): List<CommentEntity>

    suspend fun getCommentsByPostIdService(postId: Int): Result<List<Comment>>

    suspend fun insertComment(commentEntity: CommentEntity): Result<Unit>
}