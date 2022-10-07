package br.com.packkmobile.network.di

import br.com.packkmobile.network.ApiOkHttpClientFactory
import br.com.packkmobile.network.ApiServiceFactory
import br.com.packkmobile.network.executor.SafeRequest
import br.com.packkmobile.network.httpclient.OkHttpClientFactory
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectNetworkKoinModule() = loadKoinModule

private val loadKoinModule by lazy {
    loadKoinModules(
        module {
            factory<OkHttpClientFactory> {
                ApiOkHttpClientFactory()
            }
            factory { SafeRequest(Dispatchers.IO) }
            single { ApiServiceFactory(get()) }
        }
    )
}