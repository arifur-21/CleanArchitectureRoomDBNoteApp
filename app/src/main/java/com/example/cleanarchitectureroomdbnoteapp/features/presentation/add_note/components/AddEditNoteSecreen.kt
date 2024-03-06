package com.example.cleanarchitectureroomdbnoteapp.features.presentation.add_note.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cleanarchitectureroomdbnoteapp.features.data.model.Note
import com.example.cleanarchitectureroomdbnoteapp.features.presentation.add_note.event.AddEditNoteEvent
import com.example.cleanarchitectureroomdbnoteapp.features.presentation.add_note.view_model.AddEditViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditNoteScreen(
    noteColor: Int,
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel()
) {

    val title = viewModel.titleState.value
    val content = viewModel.contentState.value

    var snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event->
            when(event){
                is AddEditViewModel.UiEvent.ShowSnackbar->{
                    snackbarHostState.showSnackbar(message = event.message)
                }
                is AddEditViewModel.UiEvent.SaveNote->{
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditNoteEvent.SaveNote)
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
            snackbarHostState = snackbarHostState
        }
    ) {

    }

    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Note.noteColor.forEach { color ->
                val colorInt = color.toArgb()

                Box(modifier = Modifier
                    .size(50.dp)
                    .shadow(15.dp, CircleShape)
                    .clip(CircleShape)
                    .background(color)
                    .border(
                        width = 3.dp,
                        color = if (viewModel.colorState.value == colorInt) {
                            Color.Black
                        } else
                            Color.Transparent,
                        shape = CircleShape
                    )
                    .clickable {
                        viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        CustomTextField(text = title.text,
            hint =  "Enter Title",
            onValueChange = {
            viewModel.onEvent(AddEditNoteEvent.EnterTilte(it))
        }, onFocusChange = {
            viewModel.onEvent(AddEditNoteEvent.ChangeTitle(it))
        })
        Spacer(modifier = Modifier.height(12.dp))

        CustomTextField(text = content.text,
            hint = "Enter description",
            onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnterContent(it)) },
            onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangeContent(it)) })
    }

}