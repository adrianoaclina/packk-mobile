package br.com.packkmobile.home.presentation.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.packkmobile.home.domain.entity.Comment
import br.com.packkmobile.home.domain.usecase.CommentUseCases
import br.com.packkmobile.network.entity.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val commentUseCases: CommentUseCases
) : ViewModel() {

    private val _state = MutableStateFlow<PostsUiState>(PostsUiState.Loading)
    val state: StateFlow<PostsUiState> = _state

    fun onEvent(event: PostsEvent) {
        when (event) {
            is PostsEvent.OpenComments -> {
                getComments(event.postId)
            }
            is PostsEvent.InsertComment -> {
                postComment(event.comment)
            }
        }
    }

    private fun postComment(comment: Comment) {
        viewModelScope.launch {
            _state.value = PostsUiState.Loading
            commentUseCases.addComment(comment).onSuccess {
                getComments(comment.postId)
            }

        }
    }

    private fun getComments(postId: Int) {
        viewModelScope.launch {
            val comments: ArrayList<Comment> = ArrayList()
            commentUseCases.getComments(postId)
                .onEach { comment ->
                    comments.add(comment)
                }
            _state.value = PostsUiState.LoadedComments(comments)
        }
    }
}