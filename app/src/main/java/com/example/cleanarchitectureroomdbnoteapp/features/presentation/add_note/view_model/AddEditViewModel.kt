package com.example.cleanarchitectureroomdbnoteapp.features.presentation.add_note.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import com.example.cleanarchitectureroomdbnoteapp.features.domain.use_case.NoteUseCase
import com.example.cleanarchitectureroomdbnoteapp.features.presentation.add_note.event.AddEditNoteEvent
import com.example.cleanarchitectureroomdbnoteapp.features.presentation.add_note.state.NoteTextState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    val _titleState  = mutableStateOf(NoteTextState(hint = "Enter title..."))
    val titleState : State<NoteTextState> = _titleState

    val _contentState = mutableStateOf(NoteTextState(hint = "Enter content..."))
    val contentState : State<NoteTextState> = _contentState

    val _colorState = mutableStateOf(Note.noteColor.random().toArgb())
    val colorState : State<Int> = _colorState

    private var currentNoteId: Int? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId->
            if (noteId != -1){

                viewModelScope.launch {
                    noteUseCase.getNoteUseCase(noteId)?.also {note->

                        currentNoteId = note.id
                        _titleState.value = titleState.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _contentState.value = contentState.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _colorState.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.EnterTilte->{
                _titleState.value = titleState.value.copy(text = event.value)
            }
            is  AddEditNoteEvent.ChangeTitle->{
                _titleState.value = titleState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            titleState.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnterContent->{
                _contentState.value = contentState.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeContent->{
                _contentState.value = contentState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                    titleState.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor->{
                _colorState.value = event.color
            }
            is AddEditNoteEvent.SaveNote->{
                viewModelScope.launch {
                    try {
                        noteUseCase.addNoteUseCase(
                            Note(
                                title = titleState.value.text,
                                content = contentState.value.text,
                                timeStamp = System.currentTimeMillis(),
                                color = colorState.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote )
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = "Save Note"
                            )
                        )

                    }catch (e: Exception){
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }

            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }


}