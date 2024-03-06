package com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case

import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import com.example.cleanarchitectureroomdbnoteapp.features.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun  invoke(id: Int): Note?{
        return repository.getNoteById(id)
    }
}