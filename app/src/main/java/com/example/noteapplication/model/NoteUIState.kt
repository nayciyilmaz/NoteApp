package com.example.noteapplication.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class NoteUIState(
    val title: String = "",
    val description: String = "",
    val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
)
