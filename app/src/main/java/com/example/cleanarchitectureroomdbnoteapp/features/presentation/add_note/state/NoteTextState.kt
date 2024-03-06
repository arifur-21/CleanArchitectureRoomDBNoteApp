package com.example.cleanarchitectureroomdbnoteapp.features.presentation.add_note.state

data class NoteTextState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
