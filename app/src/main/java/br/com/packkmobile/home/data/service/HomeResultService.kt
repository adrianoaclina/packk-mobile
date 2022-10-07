package br.com.packkmobile.home.data.service

import br.com.packkmobile.home.domain.entity.Post
import retrofit2.http.GET

interface HomeResultService {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}