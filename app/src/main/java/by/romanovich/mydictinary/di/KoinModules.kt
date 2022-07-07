package by.romanovich.mydictinary.di

import androidx.room.Room
import by.romanovich.mydictinary.data.DataModel
import by.romanovich.mydictinary.data.RepositoryImpl
import by.romanovich.mydictinary.data.RepositoryLocalImpl
import by.romanovich.mydictinary.data.room.HistoryDataBase
import by.romanovich.mydictinary.domain.datasource.RetrofitImplementation
import by.romanovich.mydictinary.domain.datasource.RoomDataBaseImpl
import by.romanovich.mydictinary.domain.repository.Repository
import by.romanovich.mydictinary.domain.repository.RepositoryLocal
import by.romanovich.mydictinary.ui.history.HistoryInteractor
import by.romanovich.mydictinary.ui.history.HistoryViewModel
import by.romanovich.mydictinary.ui.main.MainInteractor
import by.romanovich.mydictinary.ui.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(
            get(), HistoryDataBase::class.java,
            "HistoryDB"
        ).build()
    }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> {
        RepositoryImpl(RetrofitImplementation())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryLocalImpl(RoomDataBaseImpl(get()))
    }
}
val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}
val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}

