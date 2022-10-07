package br.com.packkmobile.home.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.packkmobile.home.domain.entity.CommentEntity

@Dao
interface CommentDao {

    @Query("SELECT * FROM CommentEntity where postId = :postId")
    fun getCommentByPostId(postId: Int): List<CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(commentEntity: CommentEntity)

}