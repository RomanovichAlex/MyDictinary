package by.romanovich.mydictinary.domain.datasource

import by.romanovich.mydictinary.data.AppState


interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}