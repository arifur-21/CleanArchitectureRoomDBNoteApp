package com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case

import androidx.compose.ui.text.toLowerCase
import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import com.example.cleanarchitectureroomdbnoteapp.features.domain.repository.NoteRepository
import com.example.cleanarchitectureroomdbnoteapp.features.domain.utils.NoteOrder
import com.example.cleanarchitectureroomdbnoteapp.features.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCAse (
    private val repository: NoteRepository
) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>>{

        return repository.getAllNote().map { note->
            when(noteOrder.orderType){
                is OrderType.Ascending->{
                    when(noteOrder){
                        is NoteOrder.Title->note.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date->note.sortedBy { it.timeStamp }
                        is NoteOrder.Color->note.sortedBy { it.color }
                    }

                }
                is  OrderType.Descending->{
                    when(noteOrder){
                        is NoteOrder.Title->note.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date->note.sortedByDescending { it.timeStamp }
                        is NoteOrder.Color->note.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}