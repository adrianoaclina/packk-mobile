package br.com.packkmobile.home.presentation.post

import br.com.packkmobile.home.domain.entity.Comment

sealed class PostsUiState {
    object Loading : PostsUiState()
    class LoadedComments(val comments: List<Comment>) : PostsUiState()
}
