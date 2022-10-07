package br.com.packkmobile.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.packkmobile.home.domain.repository.HomeResultRepository
import br.com.packkmobile.network.entity.onError
import br.com.packkmobile.network.entity.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeResultRepository: HomeResultRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {
        _uiState.value = HomeUiState.Loading
        homeResultRepository.getPosts()
            .onSuccess { posts ->
                _uiState.value = HomeUiState.LoadedPosts(
                    posts
                )
            }
            .onError { error ->
                _uiState.value = HomeUiState.Error(
                    error.message
                )
            }
    }
}