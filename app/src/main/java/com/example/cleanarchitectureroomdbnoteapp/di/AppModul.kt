package com.example.cleanarchitectureroomdbnoteapp.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchitectureroomdbnoteapp.features.data.data_source.local_db.NoteDatabase
import com.example.cleanarchitectureroomdbnoteapp.features.data.repository.NoteRepositoryImpl
import com.example.cleanarchitectureroomdbnoteapp.features.domain.repository.NoteRepository
import com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case.AddNoteUseCase
import com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case.DeleteNoteUseCase
import com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case.GetNoteUseCase
import com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case.GetNotesUseCAse
import com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModul {


    @Provides
    @Singleton
    fun provideDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app,
           NoteDatabase::class.java,
            "note_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCase{
        return NoteUseCase(
            addNoteUseCase = AddNoteUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository),
            getNotesUseCAse = GetNotesUseCAse(repository)
        )
    }
}