package by.romanovich.mydictinary.ui.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.romanovich.mydictinary.data.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    // Завершаем все незавершённые корутины, потому что пользователь закрыл
// экран
    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    abstract fun getData(word: String, isOnline: Boolean)

    // обрабатываем ошибки в конкретной имплементации базовой ВьюМодели
    abstract fun handleError(error: Throwable)
}


