package br.com.packkmobile.home.presentation.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.packkmobile.R
import br.com.packkmobile.home.domain.entity.Post
import br.com.packkmobile.home.presentation.animations.LoadingAnimation
import br.com.packkmobile.home.presentation.post.comments.CommentsContent
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardPost(
    post: Post,
    viewModel: PostViewModel = getViewModel()
) {
    val commentsState = remember { mutableStateOf(false) }
    Card(
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 10.dp),
        onClick = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Column(
                modifier = Modifier.clickable {
                    if (!commentsState.value) {
                        viewModel.onEvent(PostsEvent.OpenComments(postId = post.id))
                    }
                    commentsState.value = !commentsState.value
                }
            ) {
                CardPostContent(
                    post = post,
                    commentsState = commentsState
                )
            }
            if (commentsState.value) {
                when (val state = viewModel.state.collectAsState().value) {
                    is PostsUiState.LoadedComments -> {
                        CommentsContent(
                            comments = state.comments,
                            post = post,
                            onSendComment = { comment ->
                                viewModel.onEvent(
                                    PostsEvent.InsertComment(
                                        comment = comment
                                    )
                                )
                            }
                        )
                    }
                    is PostsUiState.Loading -> {
                        LoadingAnimation(modifier = Modifier.padding(top = 10.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun CardPostContent(
    post: Post,
    commentsState: MutableState<Boolean>
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(.9f)) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
                text = post.title,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = post.body,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
        Image(
            painter = painterResource(
                id = if (commentsState.value) {
                    R.drawable.ic_baseline_keyboard_arrow_down_24
                } else {
                    R.drawable.ic_baseline_keyboard_arrow_right_24
                }
            ),
            contentDescription = "",
            modifier = Modifier
                .weight(.1f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardPostPreview() {
    val commentsState = remember { mutableStateOf(true) }
    CardPostContent(
        post = Post(
            userId = 1,
            id = 1,
            title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum" +
                    "\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem" +
                    " sunt rem eveniet architecto"
        ),
        commentsState
    )
}