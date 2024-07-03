package di
/*
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.local.AppPreferencesRepositoryImp
import data.local.WatchProgressRepository
import data.local.datastore.createDataStore
import data.remote.CourseRepositoryImp
import domain.repository.AppPreferencesRepository
import domain.repository.CourseRepository
import domain.use_cases.GetCoursesUseCase
import domain.use_cases.GetChaptersUseCase
import domain.use_cases.GetUserNameUseCase
import domain.use_cases.GetChaptersWithPercentageUseCase
*/

import data.local.NoteRepositoryImp
import domain.repository.NoteRepository
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.NoteViewModel

val provideRepositoryModule = module {
    single<NoteRepository> { NoteRepositoryImp() }
}

val provideViewModelModule = module {
    viewModelOf(::NoteViewModel)
}

fun appModule() = listOf(provideRepositoryModule, provideViewModelModule)