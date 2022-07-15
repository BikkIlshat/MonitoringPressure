package com.bikk.monitoringpressure.data.models

data class Health(
    val time: String? = "",
    val pulse: Int? = 0,
    var heartBreak: Int? = 0,
    var heartBeat: Int? = 0
)
