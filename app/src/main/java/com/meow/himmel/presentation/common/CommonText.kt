package com.meow.himmel.presentation.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorText(
    text: String = "Error",
    isCentered: Boolean = false,
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.titleSmall,
        textAlign = if (isCentered) TextAlign.Center else TextAlign.Start,
    )
}