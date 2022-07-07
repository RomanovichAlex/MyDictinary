package by.romanovich.mydictinary.ui.history

import android.os.Bundle
import android.widget.Toast
import by.romanovich.utils.isOnline
import by.romanovich.mydictinary.data.AppState
import by.romanovich.mydictinary.data.DataModel
import by.romanovich.mydictinary.ui.base.BaseActivity
import by.romanovich.mydictinary.databinding.ActivityHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    override var isNetworkAvailable: Boolean = false
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isNetworkAvailable = isOnline(applicationContext)
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        model.getData("", false)
    }

    private fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity) { renderData(it) }
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.data?.let {
                    if (it.isEmpty()) {
                        Toast.makeText(this@HistoryActivity, "Пусто", Toast.LENGTH_SHORT).show()
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                Toast.makeText(this@HistoryActivity, "Загрузка", Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                val message = appState.error.message
                Toast.makeText(this@HistoryActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}