package br.com.packkmobile.home.data.repository

import br.com.packkmobile.home.data.service.HomeResultService
import br.com.packkmobile.home.domain.entity.Post
import br.com.packkmobile.home.domain.repository.HomeResultRepository
import br.com.packkmobile.network.executor.SafeRequest

class HomeResultRepositoryImpl(
    private val homeResultService: HomeResultService,
    private val safeRequest: SafeRequest
): HomeResultRepository {

    override suspend fun getPosts() = safeRequest {
        homeResultService.getPosts()
    }

}