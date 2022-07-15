package com.bikk.monitoringpressure.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bikk.monitoringpressure.R
import com.bikk.monitoringpressure.data.AppState
import com.bikk.monitoringpressure.data.models.Health
import com.bikk.monitoringpressure.databinding.FragmentMainBinding
import org.koin.android.ext.android.getKoin
import org.koin.core.scope.Scope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bikk.monitoringpressure.ui.dialog.HealthDialog

private const val DIALOG_FRG = "dialog"

class MainFragment : Fragment(R.layout.fragment_main) {
    private val scope: Scope by lazy { getKoin().createScope<MainFragment>() }
    private val viewBinding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel = scope.get()


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                if (appState.data is List<*>) {
                    val adapter = MainAdapter(appState.data as List<Health>)
                    viewBinding.recycleViewHeal.adapter = adapter
                }
            }

            is AppState.Error -> Toast.makeText(
                requireContext(),
                appState.error.message,
                Toast.LENGTH_LONG
            ).show()
            else -> {}
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it as AppState) }
        viewModel.getData()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myFab = viewBinding.fab
        myFab.setColorFilter(Color.WHITE)

        viewBinding.fab.setOnClickListener {
            HealthDialog(viewModel).show(childFragmentManager, DIALOG_FRG)
        }
    }
}