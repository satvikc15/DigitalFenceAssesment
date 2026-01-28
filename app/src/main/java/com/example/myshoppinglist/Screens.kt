package com.example.myshoppinglist

sealed class Screens(val route:String) {
    object HomeScreen : Screens("HomeView")
    object AddEditScreen : Screens("addEditView")
}