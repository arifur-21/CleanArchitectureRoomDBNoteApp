package com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case

import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import com.example.cleanarchitectureroomdbnoteapp.features.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun  invoke(note: Note){
        repository.deleteNote(note)
    }
}