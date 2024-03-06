package com.example.cleanarchitectureroomdbnoteapp.features.domain.utils

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
