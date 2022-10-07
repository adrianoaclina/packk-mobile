package br.com.packkmobile.home.presentation.home

import br.com.packkmobile.home.domain.entity.Post

sealed class HomeUiState {
    object Loading : HomeUiState()
    class LoadedPosts(val posts: List<Post>) : HomeUiState()
    class Error(val message: String) : HomeUiState()
}