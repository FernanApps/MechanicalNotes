package presentation

import androidx.lifecycle.ViewModel
import domain.model.Note
import domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NoteViewModel: ViewModel(), KoinComponent {
    val repository: NoteRepository by inject()

    val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    init {
        _notes.value = repository.getAllNotes()
    }

}