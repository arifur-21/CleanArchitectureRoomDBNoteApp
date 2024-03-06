package com.example.cleanarchitectureroomdbnoteapp.features.presentation.notes.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case.NoteUseCase
import com.example.cleanarchitectureroomdbnoteapp.features.domain.utils.NoteOrder
import com.example.cleanarchitectureroomdbnoteapp.features.domain.utils.OrderType
import com.example.cleanarchitectureroomdbnoteapp.features.presentation.notes.event.NotesEvent
import com.example.cleanarchitectureroomdbnoteapp.features.presentation.notes.state.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
): ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state : State<NotesState> = _state

    private var recentlyDeleteNote: Note? = null

    init {
        getNote(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.GetNoteOrder->{
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType){
                    return
                }
                getNote(event.noteOrder)
            }
            is  NotesEvent.DeleteNote->{
                viewModelScope.launch {
                    noteUseCase.deleteNoteUseCase(event.note)
                    recentlyDeleteNote = event.note
                }
            }
            is NotesEvent.RestoreNote->{
                viewModelScope.launch {
                    noteUseCase.addNoteUseCase(
                        recentlyDeleteNote ?: return@launch)
                    recentlyDeleteNote = null
                }

            }
            is NotesEvent.ToggleOrderSection->{
                _state.value = NotesState(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNote(noteOrder: NoteOrder){

        noteUseCase.getNotesUseCAse(noteOrder).onEach { notes ->
            _state.value = state.value.copy(
                notes = notes,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)

    }


}