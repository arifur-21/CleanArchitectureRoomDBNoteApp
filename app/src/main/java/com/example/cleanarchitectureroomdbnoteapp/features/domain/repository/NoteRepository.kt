package com.example.cleanarchitectureroomdbnoteapp.features.domain.repository

import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {


    fun getAllNote(): Flow<List<Note>>
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getNoteById(id: Int): Note?
}