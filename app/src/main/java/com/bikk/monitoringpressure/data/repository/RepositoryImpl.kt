package com.bikk.monitoringpressure.data.repository

import com.bikk.monitoringpressure.data.datasource.DataSource
import com.bikk.monitoringpressure.data.models.Health
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val cloudSource: DataSource
) : Repository {
    override fun getData(): Flow<List<Health>> = cloudSource.getData()

    override suspend fun setData(health: Health) = cloudSource.setData(health)
}