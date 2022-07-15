package com.bikk.monitoringpressure.ui.main.viewholders

import android.annotation.SuppressLint
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bikk.monitoringpressure.R
import com.bikk.monitoringpressure.data.models.Health
import com.bikk.monitoringpressure.databinding.ItemDataBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ItemDateCreateViewHolder(
    private val viewBinding: ItemDataBinding
) : RecyclerView.ViewHolder(viewBinding.root) {
    @SuppressLint("NewApi")
    fun bind(health: Health) {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)
        val currentDate = LocalDateTime.parse(health.time, formatter)
        val timeDateFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val time = currentDate.format(timeDateFormatter)
        viewBinding.pulse.text = health.pulse.toString()
        viewBinding.heartBreak.text = health.heartBreak.toString()
        viewBinding.heart.text = health.heartBeat.toString()
        viewBinding.time.text = time
        if (checkNotNull(health.heartBeat) >= 90) {
            viewBinding.containerItemHealthInfo.background =
                AppCompatResources.getDrawable(viewBinding.root.context, R.drawable.back_ground_red)
        } else {
            viewBinding.containerItemHealthInfo.background =
                AppCompatResources.getDrawable(
                    viewBinding.root.context,
                    R.drawable.back_ground_green
                )
        }
    }
}