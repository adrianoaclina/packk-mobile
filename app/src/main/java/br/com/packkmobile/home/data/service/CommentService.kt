package br.com.packkmobile.home.data.service

import br.com.packkmobile.home.domain.entity.Comment
import br.com.packkmobile.home.domain.entity.CommentBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentService {

    @GET("posts/{postId}/comments")
    suspend fun getPosts(
        @Path("postId") postId: Int
    ): List<Comment>

    @POST("posts/{postId}/comments")
    suspend fun postComments(
        @Body comment: CommentBody
    ): Unit
}