package presentation

import androidx.lifecycle.ViewModel
import domain.model.Note
import domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NoteViewModel : ViewModel(), KoinComponent {

    private val repository: NoteRepository by inject()

    private val _notes = MutableStateFlow<List<Note>>(emptyList())

    private val _notesFiltered = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notesFiltered.asStateFlow()


    private val _searchText = MutableStateFlow<String>("")
    val searchText = _searchText.asStateFlow()

    init {
        _notes.value = repository.getAllNotes()
        onSearchTextChanged("")
    }

    fun onSearchTextChanged(newText: String) {
        _searchText.value = newText
        val filteredList =
            _notes.value.filter {
                it.vehicle.lowercase().contains(newText.lowercase()) || it.anomalies.any { anomaly ->
                    anomaly.description.lowercase().contains(newText.lowercase())
                }
            }
        _notesFiltered.value = filteredList
    }


}