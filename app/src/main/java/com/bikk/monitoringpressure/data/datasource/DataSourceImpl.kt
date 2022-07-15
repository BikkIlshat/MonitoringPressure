package com.bikk.monitoringpressure.data.datasource

import com.bikk.monitoringpressure.data.models.Health
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class DataSourceImpl(
    private val db: FirebaseDatabase
) : DataSource {
    companion object {
        private const val dbName = "Health3"
    }

    override fun getData(): Flow<List<Health>> = callbackFlow<List<Health>> {
        db.getReference(dbName).get().addOnCompleteListener { task ->
            val list = mutableListOf<Health>()
            task.result?.children?.map {
                it.getValue(Health::class.java)?.let { it1 -> list.add(it1) }
            }
            trySend(list)
        }


        awaitClose {
            db.getReference(dbName).removeValue()
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun setData(health: Health) {
        db.getReference(dbName).push().setValue(health)
    }

}