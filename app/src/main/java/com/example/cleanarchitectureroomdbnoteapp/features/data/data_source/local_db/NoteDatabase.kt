package com.example.cleanarchitectureroomdbnoteapp.features.data.data_source.local_db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase(){

    abstract val noteDao : NoteDao
}