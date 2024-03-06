package com.example.cleanarchitectureroomdbnoteapp.features.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleanarchitectureroomdbnoteapp.ui.theme.BabyBlue
import com.example.cleanarchitectureroomdbnoteapp.ui.theme.LightGreen
import com.example.cleanarchitectureroomdbnoteapp.ui.theme.RedOrange
import com.example.cleanarchitectureroomdbnoteapp.ui.theme.RedPink
import com.example.cleanarchitectureroomdbnoteapp.ui.theme.Violet


@Entity
data class Note(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int
){
    companion object {
        val noteColor = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
