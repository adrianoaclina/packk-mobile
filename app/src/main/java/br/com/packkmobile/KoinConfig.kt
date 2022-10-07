package br.com.packkmobile

import br.com.packkmobile.home.presentation.di.injectHomeKoinModule
import br.com.packkmobile.network.di.injectNetworkKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun startKoin(application: Application){

    startKoin {
        androidContext(application)
    }

    injectModules()
}

fun injectModules() {
    injectNetworkKoinModule()
    injectHomeKoinModule()
}
