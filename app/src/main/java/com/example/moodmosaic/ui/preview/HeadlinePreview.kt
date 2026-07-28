package com.example.moodmosaic.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodmosaic.ui.components.HeadlineLarge
import com.example.moodmosaic.ui.components.HeadlineMedium
import com.example.moodmosaic.ui.theme.MoodMosaicTheme

@Preview(showBackground = true)
@Composable
fun HeadlinePreview() {
    MoodMosaicTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp, 0.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                HeadlineLarge(
                    text = "Große Überschrift",
                    paddingValues = PaddingValues(16.dp)
                )

                HeadlineMedium(
                    text = "Mittlere Überschrift",
                    paddingValues = PaddingValues(horizontal = 16.dp)
                )
            }
        }
    }
}