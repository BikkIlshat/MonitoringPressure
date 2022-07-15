package com.bikk.monitoringpressure.di

import com.bikk.monitoringpressure.data.datasource.DataSource
import com.bikk.monitoringpressure.data.datasource.DataSourceImpl
import com.bikk.monitoringpressure.data.repository.Repository
import com.bikk.monitoringpressure.data.repository.RepositoryImpl
import com.bikk.monitoringpressure.ui.main.MainFragment
import com.bikk.monitoringpressure.ui.main.MainViewModel
import com.google.firebase.database.FirebaseDatabase

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Di {
    fun sourceModule() = module {
        single {
            FirebaseDatabase.getInstance()
        }
    }

    fun cloudSourceModule() = module {
        single<DataSource> {
            DataSourceImpl(get())
        }
    }

    fun repositoryModule() = module {
        single<Repository> {
            RepositoryImpl(get())
        }
    }

    fun viewModelsModule() = module {
        scope<MainFragment> {
            viewModel { MainViewModel(get()) }
        }
    }
}