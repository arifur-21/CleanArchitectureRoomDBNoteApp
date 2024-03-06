package com.example.cleanarchitectureroomdbnoteapp.features.presentation.add_note.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color

@Composable
fun CustomTextField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
) {

    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = { Text(text = hint)},
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                onFocusChange(it)
            }
    )






}