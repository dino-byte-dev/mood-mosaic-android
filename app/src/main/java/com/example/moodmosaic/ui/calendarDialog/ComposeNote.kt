package com.example.moodmosaic.ui.calendarDialog

import LabelSmall
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.moodmosaic.ui.components.HeadlineMedium
import com.example.moodmosaic.ui.theme.calendarEmptyDay
import com.example.moodmosaic.ui.theme.trueBlack

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ComposeNote(
    dateString: String,
    noteText: String,
    onNoteChange: (String) -> Unit,
    innerPadding: PaddingValues
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val nPadding = PaddingValues(
        horizontal = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
        vertical = 0.dp,
    )

    HeadlineMedium(text = "Notiz (optional): ", paddingValues = nPadding)

    OutlinedTextField(
        minLines = 5,
        value = noteText,
        onValueChange = onNoteChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        label = { LabelSmall("Notiz für ${dateString}") },
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        ),
        placeholder = {
            Text("Beschreibe deinen Tag.")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
            .defaultMinSize(20.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.calendarEmptyDay,
            focusedIndicatorColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.trueBlack
        )
    )
}