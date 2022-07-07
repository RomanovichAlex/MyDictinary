package by.romanovich.mydictinary.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.romanovich.utils.isOnline
import by.romanovich.mydictinary.ui.viewModel.BaseViewModel
import by.romanovich.mydictinary.ui.viewModel.Interactor
import by.romanovich.mydictinary.data.AppState


abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    protected open var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
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

