package com.bikk.monitoringpressure.data.repository

import com.bikk.monitoringpressure.data.models.Health
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getData() : Flow<List<Health>>
    suspend fun setData(health: Health)
}