package com.example.myshoppinglist


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class listViewModel(
    private val repository: DataRepository = Graph.DataRepository
): ViewModel(){



    val getAllWishes: StateFlow<List<ListEntity>> = repository.getAllList()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

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
