package by.romanovich.mydictinary.ui.history

import androidx.lifecycle.LiveData
import by.romanovich.mydictinary.data.AppState
import by.romanovich.mydictinary.domain.utils.parseLocalSearchResults
import by.romanovich.mydictinary.ui.viewModel.BaseViewModel
import kotlinx.coroutines.launch


class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(
            parseLocalSearchResults(
                interactor.getData(
                    word,
                    isOnline
                )
            )
        )
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null) // Set View to
// original state in
// onStop
        super.onCleared()
    }
}
