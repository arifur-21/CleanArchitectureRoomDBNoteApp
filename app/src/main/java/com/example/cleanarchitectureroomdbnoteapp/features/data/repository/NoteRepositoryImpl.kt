package com.example.cleanarchitectureroomdbnoteapp.features.data.repository

import com.example.cleanarchitectureroomdbnoteapp.features.data.data_source.local_db.NoteDao
import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import com.example.cleanarchitectureroomdbnoteapp.features.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl (
    private val  noteDao: NoteDao
): NoteRepository {
    override fun getAllNote(): Flow<List<Note>> {
        return noteDao.getAllNote()

    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id = id )
    }
}