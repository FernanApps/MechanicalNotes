package domain.repository

import domain.model.Note

interface NoteRepository {
    fun getAllNotes(): List<Note>
}