package com.example.moodmosaic.ui.calendarDialog

import ButtonHighlighted
import ButtonSubtle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.moodmosaic.R
import com.example.moodmosaic.db.entities.MoodDefinition
import com.example.moodmosaic.ui.components.HeadlineLarge

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CreateEntry(
    padding: PaddingValues,
    dateString: String,
    initialNote: String,
    moodDefinitions: List<MoodDefinition>,
    initialMood: Long,
    onDismiss: () -> Unit,
    onSave: (String, Long) -> Unit
) {
    var noteText by remember { mutableStateOf(initialNote) }
    var moodId by remember { mutableLongStateOf(initialMood) }
    val scrollState = rememberScrollState()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp, 0.dp)
                    .verticalScroll(scrollState)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                HeadlineLarge(dateString, padding)

                ChooseMood(
                    selectedMoodId = moodId,
                    onMoodChange = { newMood ->
                        moodId = newMood
                    },
                    innerPadding = padding,
                    moodDefinitions = moodDefinitions
                )

                ComposeNote(
                    dateString = dateString,
                    noteText = noteText,
                    onNoteChange = { newText ->
                        noteText = newText
                    },
                    innerPadding = padding
                )

                EntryActionBar(
                    paddingValues = padding,
                    onDismiss = onDismiss,
                    onSave = onSave,
                    noteText = noteText,
                    moodId = moodId
                )
            }
        }
    }
}

@Composable
fun EntryActionBar(
    paddingValues: PaddingValues,
    onDismiss: () -> Unit,
    onSave: (String, Long) -> Unit,
    noteText: String,
    moodId: Long
) {
    Column {
        Row(Modifier
            .fillMaxWidth()
            .padding(paddingValues)) {
            ButtonSubtle(
                text = "Abbrechen",
                icon = ImageVector.vectorResource(R.drawable.close_24px),
                onClick = { onDismiss() }
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            ButtonHighlighted(
                text = "Speichern",
                icon = ImageVector.vectorResource(R.drawable.save_24px),
                onClick = { onSave(noteText, moodId) },
                iconPadding = 4.dp
            )
        }
    }
}