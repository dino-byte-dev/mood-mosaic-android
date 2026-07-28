import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.moodmosaic.R

@Composable
fun ButtonSubtle(
    onClick: () -> Unit,
    icon: ImageVector? = null,
    text: String,
) {
    OutlinedButton(
        shape = RoundedCornerShape(6.dp),
        onClick = { onClick() },
    ) {
        icon?.let {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.close_24px),
                contentDescription = text
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}