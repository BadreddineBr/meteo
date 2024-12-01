package com.example.meteoApp.feature.detail

import android.content.res.Configuration
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.meteoApp.core.BaseFragment
import com.example.meteoApp.databinding.FragmentWeatherDetailScreenBinding
import com.example.meteoApp.feature.detail.adapter.OnDayClickListener
import com.example.meteoApp.feature.detail.adapter.SelectableDailyForecastAdapter
import com.example.meteoApp.feature.home.adapter.HourlyForecastAdapter
import com.example.meteoApp.ui.ItemDailyForecastModel
import com.example.meteoApp.ui.PaddingDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherDetailScreenFragment : BaseFragment<FragmentWeatherDetailScreenBinding>() {

    private val viewModel: WeatherDetailViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWeatherDetailScreenBinding =
        FragmentWeatherDetailScreenBinding.inflate(inflater, container, false)

    override fun FragmentWeatherDetailScreenBinding.onMain() {
        mtToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        mtToolbar.setNavigationIconTint(
            when (context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> Color.GRAY
                else -> Color.BLACK
            }
        )

        val dailyForecastAdapter = SelectableDailyForecastAdapter(
            onDayClickListener = object : OnDayClickListener {
                override fun onDayClick(itemDailyForecastModel: ItemDailyForecastModel) {
                    viewModel.setWeatherData(itemDailyForecastModel)
                }
            }
        )
        rvDaily.apply {
            adapter = dailyForecastAdapter
            addItemDecoration(PaddingDecoration(16, 16, 0, 0))

        }

        val hourlyForecastAdapter = HourlyForecastAdapter()
        includeWeatherCard.rvTodayWeatherData.apply {
            adapter = hourlyForecastAdapter
            addItemDecoration(PaddingDecoration(16, 16, 0, 0))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    if (uiState.currentWeatherInfo != null) {
                        currentWeatherInfo = uiState.currentWeatherInfo
                    }

                    if (uiState.dailyForecast.isNotEmpty()) {
                        dailyForecastAdapter.submitList(uiState.dailyForecast)
                    }

                    if (uiState.weatherDetails != null) {
                        weatherDetails = uiState.weatherDetails
                    }

                    if (uiState.todayThreeHourlyForecast.isNotEmpty()) {
                        hourlyForecastAdapter.submitList(uiState.todayThreeHourlyForecast)
                    }
                }
            }
        }
    }
}