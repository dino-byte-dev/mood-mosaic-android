package com.example.moodmosaic.ui.preview

import ButtonHighlighted
import ButtonSubtle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodmosaic.R
import com.example.moodmosaic.ui.theme.MoodMosaicTheme

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    MoodMosaicTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            ButtonHighlighted(
                text = "Eintrag speichern",
                onClick = {},
                icon = ImageVector.vectorResource(R.drawable.save_24px),
            )

            ButtonSubtle(
                text = "Abbrechen",
                icon = ImageVector.vectorResource(R.drawable.close_24px),
                onClick = {}
            )
        }
    }
}