package com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case

import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import com.example.cleanarchitectureroomdbnoteapp.features.domain.repository.NoteRepository

class AddNoteUseCase (
    private val repository: NoteRepository
) {

    suspend operator fun  invoke(note: Note){
        if (note.title.isEmpty()){
            throw Exception("enter title")
        }
        if (note.content.isEmpty()){
            throw Exception("enter content")
        }
        repository.insertNote(note)
    }
}