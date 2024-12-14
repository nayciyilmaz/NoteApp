package com.example.noteapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapplication.components.EditTextField
import com.example.noteapplication.model.NoteUIState
import com.example.noteapplication.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    noteViewModel: NoteViewModel = viewModel()
) {
    val uiState by noteViewModel.uiState.collectAsState()

    Column(modifier.padding(8.dp)) {
        TopAppBar(
            title = { Text("Daily Note App") },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFFC5C5C5)),
            actions = {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    modifier = modifier.padding(end = 8.dp)
                )
            }
        )
        Column(
            modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditTextField(
                text = uiState.title,
                onValue = { noteViewModel.updateTitle(it) },
                label = "Title",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                modifier = modifier.padding(top = 8.dp)
            )

            EditTextField(
                text = uiState.description,
                onValue = { noteViewModel.updateDescription(it) },
                label = "Add Note",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            Button(
                onClick = { noteViewModel.saveNote() },
                enabled = noteViewModel.buttonEnabled(),
                modifier = modifier.padding(bottom = 8.dp)
            ) {
                Text("Save")
            }

            HorizontalDivider(thickness = 2.dp, modifier = modifier.padding(top = 8.dp))

            NoteCardList()
        }
    }
}

@Composable
fun NoteCardList(
    modifier: Modifier = Modifier,
    noteViewModel: NoteViewModel = viewModel()
) {
    val notesList by noteViewModel.noteList.collectAsState()

    LazyColumn(modifier = modifier.padding(top = 8.dp)) {
        items(notesList) { item ->
            Card(
                modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier.padding(8.dp)) {
                        Text(text = item.title, fontWeight = FontWeight.Bold)
                        Text(text = item.description, fontWeight = FontWeight.SemiBold)
                        Text(text = item.date)
                    }

                    Spacer(modifier = modifier.width(150.dp))

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = modifier.clickable {
                            noteViewModel.deleteNote(item)
                        }
                    )
                }
            }
        }
    }
}
