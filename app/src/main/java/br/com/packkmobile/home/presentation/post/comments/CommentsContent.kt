package br.com.packkmobile.home.presentation.post.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.packkmobile.home.domain.entity.Comment
import br.com.packkmobile.home.domain.entity.Post

@Composable
fun CommentsContent(
    comments: List<Comment>,
    post: Post,
    onSendComment: (Comment) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, top = 2.dp, bottom = 2.dp),
                text = "Comments",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            ListComments(comments)
            FormComment(post, onSendComment)
        }
    }
}

@Composable
private fun ListComments(comments: List<Comment>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp)
    ) {
        comments.forEach { comment ->
            CardComment(comment)
        }
    }
}

@Composable
private fun CardComment(comment: Comment) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp, top = 3.dp, bottom = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp)
        ) {
            Text(
                modifier = Modifier.weight(.4f),
                text = comment.name,
                fontSize = 12.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                modifier = Modifier.weight(.6f),
                text = comment.email,
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = comment.body,
            fontSize = 10.sp,
            color = Color.Black
        )
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(top = 1.dp)
        )
    }
}

@Composable
private fun FormComment(
    post: Post,
    onSendComment: (Comment) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = name,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                placeholder = {
                    Text(
                        text = "Type your name",
                        fontSize = TextUnit.Unspecified,
                        fontWeight = FontWeight.Normal
                    )
                },
                label = {
                    Text(
                        text = "Name",
                        fontSize = TextUnit.Unspecified,
                        fontWeight = FontWeight.Normal
                    )
                },
                onValueChange = { name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 5.dp)
                    .weight(0.5f),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = email,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                placeholder = {
                    Text(
                        text = "Type your email",
                        fontSize = TextUnit.Unspecified,
                        fontWeight = FontWeight.Normal
                    )
                },
                label = {
                    Text(
                        text = "Email",
                        fontSize = TextUnit.Unspecified,
                        fontWeight = FontWeight.Normal
                    )
                },
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 5.dp)
                    .weight(0.5f),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
        }
        var comentary by remember { mutableStateOf("") }
        OutlinedTextField(
            value = comentary,
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            ),
            placeholder = {
                Text(
                    text = "Type your comment",
                    fontSize = TextUnit.Unspecified,
                    fontWeight = FontWeight.Normal
                )
            },
            label = {
                Text(
                    text = "Comentary",
                    fontSize = TextUnit.Unspecified,
                    fontWeight = FontWeight.Normal
                )
            },
            onValueChange = { comentary = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = RoundedCornerShape(12.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
            onClick = {
                onSendComment(
                    Comment(
                        postId = post.id,
                        email = email,
                        name = name,
                        body = comentary
                    )
                )
            }
        ) {
            Text(
                text = "Send",
                fontSize = 12.sp
            )
        }
    }
}

@Composable
@Preview
fun CommentsContentPreview() {
    CommentsContent(
        comments = listOf(
            Comment(
                1,
                body = "Comment here",
                name = "Name",
                email = "email@email.com",
                id = 0
            ),
            Comment(
                1,
                body = "Comment here",
                name = "Name",
                email = "email@email.com",
                id = 0
            ),
            Comment(
                1,
                body = "Comment here",
                name = "Name",
                email = "email@email.com",
                id = 0
            )
        ),
        post = Post(
            userId = 1,
            id = 1,
            title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum" +
                    "\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem" +
                    " sunt rem eveniet architecto"
        ),
        onSendComment = {}
    )
}