package com.example.moodmosaic.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun HeadlineMedium(text: String, paddingValues: PaddingValues) {
    Text(
        text = text,
        modifier = Modifier.padding(paddingValues).background(MaterialTheme.colorScheme.inversePrimary).padding(horizontal = 12.dp, vertical = 6.dp),
        color = MaterialTheme.colorScheme.onPrimary,
        textDecoration = TextDecoration.Underline,
        style = MaterialTheme.typography.headlineMedium
    )
}