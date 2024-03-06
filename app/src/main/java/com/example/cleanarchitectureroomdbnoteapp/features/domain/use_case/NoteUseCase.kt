package com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case

data class NoteUseCase(
    val addNoteUseCase: AddNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getNoteUseCase: GetNoteUseCase,
    val getNotesUseCAse: GetNotesUseCAse
)
