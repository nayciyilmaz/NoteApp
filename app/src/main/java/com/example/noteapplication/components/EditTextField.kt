package com.example.noteapplication.components


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EditTextField(
    text: String,
    onValue: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    singleLine: Boolean
) {
    TextField(
        value = text,
        onValueChange = onValue,
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        singleLine = singleLine
    )
}