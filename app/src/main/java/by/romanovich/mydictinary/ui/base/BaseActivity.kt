package by.romanovich.mydictinary.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import by.romanovich.mydictinary.R
import by.romanovich.mydictinary.data.AppState
import by.romanovich.mydictinary.ui.viewModel.BaseViewModel
import by.romanovich.mydictinary.ui.viewModel.Interactor
import by.romanovich.utils.isOnline
import by.romanovich.utils.network.OnlineLiveData


abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    protected open var isNetworkAvailable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        subscribeToNetworkChange()

        // isNetworkAvailable = isOnline(applicationContext)
    }

    private fun subscribeToNetworkChange() {
        OnlineLiveData(this).observe(
            this@BaseActivity,
            Observer<Boolean> {
                isNetworkAvailable = it
                if (! isNetworkAvailable) {
                    Toast.makeText(
                        this@BaseActivity,
                        R.string.device_is_offline,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (! isNetworkAvailable) {
            Toast.makeText(
                this, "Device is offline",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    // Каждая Активити будет отображать какие-то данные в соответствующем состоянии
    abstract fun renderData(appState: T)


}

