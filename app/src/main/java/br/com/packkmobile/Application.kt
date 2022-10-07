package br.com.packkmobile

import android.app.Application
import android.content.Intent
import br.com.packkmobile.home.presentation.HomeActivity

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this)
    }
}