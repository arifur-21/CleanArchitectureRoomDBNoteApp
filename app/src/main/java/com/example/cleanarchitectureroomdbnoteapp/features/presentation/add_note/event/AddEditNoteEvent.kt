package com.example.cleanarchitectureroomdbnoteapp.features.presentation.add_note.event

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {

    data class EnterTilte(val value: String): AddEditNoteEvent()
    data class ChangeTitle(val focusState: FocusState): AddEditNoteEvent()
    data class EnterContent(val value: String): AddEditNoteEvent()
    data class ChangeContent(val focusState: FocusState): AddEditNoteEvent()
    data class ChangeColor(val color: Int) : AddEditNoteEvent()

    object SaveNote: AddEditNoteEvent()

}