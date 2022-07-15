package com.bikk.monitoringpressure.application

import android.app.Application
import com.bikk.monitoringpressure.di.Di
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    Di.sourceModule(),
                    Di.cloudSourceModule(),
                    Di.repositoryModule(),
                    Di.viewModelsModule()
                )
            )
        }
    }
}