package id.appliation.core.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val LightColors = lightColorScheme(
    primary = PLNBlue,
    onPrimary = White,
    primaryContainer = PLNBlueDark,
    background = White,
    surface = White,
    onSurface = Black,
    secondary = PLNSkyBlue,
    outline = BorderGray,
)

private val DarkColors = darkColorScheme(
    primary = PLNBlue,
    onPrimary = White,
    primaryContainer = PLNBlueDark,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onSurface = White,
    secondary = PLNSkyBlue,
    outline = BorderGray,
)

@Composable
fun SanguTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val context = LocalContext.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window ?: return@SideEffect
            window.statusBarColor = Color.White.toArgb()

            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
