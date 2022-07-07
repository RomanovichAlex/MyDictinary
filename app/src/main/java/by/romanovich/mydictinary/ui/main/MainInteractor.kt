package by.romanovich.mydictinary.ui.main

import by.romanovich.mydictinary.ui.viewModel.Interactor
import by.romanovich.mydictinary.data.AppState
import by.romanovich.mydictinary.data.DataModel
import by.romanovich.mydictinary.domain.repository.Repository
import by.romanovich.mydictinary.domain.repository.RepositoryLocal


class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            AppState {
        val appState: AppState
// Теперь полученное слово мы сохраняем в БД. Сделать это нужно именно
// здесь, в соответствии с принципами чистой архитектуры: интерактор
// обращается к репозиторию
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
