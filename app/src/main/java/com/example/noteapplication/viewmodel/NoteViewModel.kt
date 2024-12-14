package com.example.noteapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.noteapplication.model.NoteUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NoteUIState())
    val uiState: StateFlow<NoteUIState> = _uiState.asStateFlow()

    private val _noteList = MutableStateFlow<List<NoteUIState>>(emptyList())
    val noteList = _noteList.asStateFlow()

    fun updateTitle(newTitle: String) {
        if (newTitle.all { it.isLetter() || it.isWhitespace() }) {
            _uiState.update { currentState ->
                currentState.copy(title = newTitle)
            }
        }
    }

    fun updateDescription(newDescription: String) {
        if (newDescription.all { it.isLetter() || it.isWhitespace() }) {
            _uiState.update { currentState ->
                currentState.copy(description = newDescription)
            }
        }
    }

    fun saveNote() {
        _noteList.update { currentState ->
            currentState + NoteUIState(
                title = _uiState.value.title,
                description = _uiState.value.description
            )
        }
        _uiState.update { currentState ->
            currentState.copy(
                title = "",
                description = ""
            )
        }
    }

    fun deleteNote(note: NoteUIState) {
        _noteList.update { currentList ->
            currentList.filterNot { it == note }
        }
    }

    fun buttonEnabled(): Boolean {
        return uiState.value.title.isNotEmpty() && uiState.value.description.isNotEmpty()
    }
}