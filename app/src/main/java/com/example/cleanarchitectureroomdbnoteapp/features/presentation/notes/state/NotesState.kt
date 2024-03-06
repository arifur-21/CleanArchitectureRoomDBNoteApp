package com.example.cleanarchitectureroomdbnoteapp.features.presentation.notes.state

import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import com.example.cleanarchitectureroomdbnoteapp.features.domain.utils.NoteOrder
import com.example.cleanarchitectureroomdbnoteapp.features.domain.utils.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder : NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false

)
