package br.com.packkmobile.home.presentation.di

import androidx.room.Room
import br.com.packkmobile.Application
import br.com.packkmobile.home.data.datasource.CommentDao
import br.com.packkmobile.home.data.datasource.CommentDatabase
import br.com.packkmobile.home.data.repository.CommentRepositoryImpl
import br.com.packkmobile.home.data.repository.HomeResultRepositoryImpl
import br.com.packkmobile.home.data.service.CommentService
import br.com.packkmobile.home.data.service.HomeResultService
import br.com.packkmobile.home.domain.repository.CommentRepository
import br.com.packkmobile.home.domain.repository.HomeResultRepository
import br.com.packkmobile.home.domain.usecase.AddComment
import br.com.packkmobile.home.domain.usecase.CommentUseCases
import br.com.packkmobile.home.domain.usecase.GetComments
import br.com.packkmobile.home.presentation.home.HomeViewModel
import br.com.packkmobile.home.presentation.post.PostViewModel
import br.com.packkmobile.network.ApiServiceFactory
import br.com.packkmobile.network.executor.SafeRequest
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "post.db"

fun injectHomeKoinModule() = loadKoinModule

private val loadKoinModule by lazy {
    loadKoinModules(
        listOf(
            dbModule,
            serviceModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
    )
}

private val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { PostViewModel(get()) }
}

private val useCaseModule = module {
    factory { GetComments(get()) }
    factory { AddComment(get()) }
    factory { CommentUseCases(get(), get()) }
}

private val repositoryModule = module {
    single<HomeResultRepository> { HomeResultRepositoryImpl(get(), get()) }
    single<CommentRepository> { CommentRepositoryImpl(get(), get(), get()) }
    factory { SafeRequest(Dispatchers.IO) }
}
private val serviceModule = module {
    single { get<ApiServiceFactory>().create(HomeResultService::class.java) }
    single { get<ApiServiceFactory>().create(CommentService::class.java)}
}

private val dbModule = module {
    single<CommentDatabase> {
        Room.databaseBuilder(
            get(),
            CommentDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).allowMainThreadQueries().build()
    }
    single<CommentDao> { get<CommentDatabase>().commentDao()  }
}