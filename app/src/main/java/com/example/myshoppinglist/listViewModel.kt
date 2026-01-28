package com.example.myshoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class listViewModel(
    private val repository: DataRepository = Graph.DataRepository,
): ViewModel(){
    var ListName by mutableStateOf("")
    var ListQuant by mutableStateOf("")

    var ListPurchased by mutableStateOf(false)

    fun onListNameChange(newString: String){
        ListName =newString
    }
    fun onListQuantChange(newString:String){
        ListQuant=newString
    }

    fun onListPurchasedChange(newBoolean:Boolean){
        ListPurchased=newBoolean
    }

    lateinit var getAllWishes : Flow<List<ListEntity>>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllWishes = repository.getAllList()
        }
    }
//    val allList: StateFlow<List<ListEntity>> = repository.getAllList()
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = emptyList()
//        )

    fun getListById(id: Long): Flow<ListEntity> {
        return repository.getAList(id)
    }

    fun add(item: ListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(item)
        }
    }

    fun updateWish(item: ListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(item)
        }
    }

    fun delete(item: ListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(item)
        }
    }
}
