package com.example.cleanarchitectureroomdbnoteapp.features.presentation.notes.event

import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import com.example.cleanarchitectureroomdbnoteapp.features.domain.utils.NoteOrder

sealed class NotesEvent{
    data class GetNoteOrder(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
