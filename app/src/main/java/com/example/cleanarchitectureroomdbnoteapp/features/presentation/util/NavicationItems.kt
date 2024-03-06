package com.example.cleanarchitectureroomnoteapp.feature_note.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cleanarchitectureroomdbnoteapp.features.presentation.add_note.components.AddEditNoteScreen
import com.example.cleanarchitectureroomdbnoteapp.features.presentation.notes.NoteScreen

@Composable
fun NavegationItems(
    navHostController: NavHostController
) {

    NavHost(navController = navHostController, startDestination = Screen.NoteScreen.route){
        composable(route = Screen.NoteScreen.route){
            NoteScreen(navHostController)
        }


        composable(route = Screen.AddEditNoteScreen.route +
                "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                },

                navArgument(
                    name = "noteColor"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                },

                )
        ){
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(navController = navHostController, noteColor = color)
        }
    }
    
}