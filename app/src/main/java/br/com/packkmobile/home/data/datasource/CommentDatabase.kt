package br.com.packkmobile.home.data.datasource

import androidx.room.Database
import androidx.room.Ignore
import androidx.room.RoomDatabase
import br.com.packkmobile.home.domain.entity.CommentEntity

@Database(
    entities = [CommentEntity::class],
    version = 2,
    exportSchema = false
)

abstract class CommentDatabase: RoomDatabase() {
    abstract fun commentDao(): CommentDao
}
