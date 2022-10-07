package br.com.packkmobile.home.domain.repository

import br.com.packkmobile.home.domain.entity.Post
import br.com.packkmobile.network.entity.Result

interface HomeResultRepository {

    suspend fun getPosts() : Result<List<Post>>
}