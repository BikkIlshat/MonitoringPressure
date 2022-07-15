package com.bikk.monitoringpressure.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bikk.monitoringpressure.data.models.Health
import com.bikk.monitoringpressure.databinding.ItemDataBinding
import com.bikk.monitoringpressure.databinding.ItemDateCreateBinding
import com.bikk.monitoringpressure.ui.main.viewholders.ItemDateCreateViewHolder
import com.bikk.monitoringpressure.ui.main.viewholders.ItemDateViewHolder
import com.bikk.monitoringpressure.ui.main.viewholders.ViewHolders
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainAdapter(
    private val data: List<Health>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    @SuppressLint("NewApi")
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewHolders.ITEM_HEALTH.ordinal -> ItemDateCreateViewHolder(
                ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ViewHolders.ITEM_DATE.ordinal -> ItemDateViewHolder(
                ItemDateCreateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> ItemDateCreateViewHolder(
                ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemDateViewHolder -> holder.bind(data[position])
            is ItemDateCreateViewHolder -> holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NewApi")
    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return ViewHolders.ITEM_DATE.ordinal
        }
        val currentDate = LocalDate.parse(data[position].time, formatter)
        val postDate = LocalDate.parse(data[position - 1].time, formatter)
        return when (currentDate == postDate) {
            true -> {
                ViewHolders.ITEM_HEALTH.ordinal
            }
            false -> {
                ViewHolders.ITEM_DATE.ordinal
            }
        }
    }


}