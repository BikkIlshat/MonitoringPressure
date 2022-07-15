package com.bikk.monitoringpressure.data.datasource

import com.bikk.monitoringpressure.data.models.Health
import kotlinx.coroutines.flow.Flow

interface   DataSource {
    fun getData(): Flow<List<Health>>
    suspend fun setData(health: Health)
}