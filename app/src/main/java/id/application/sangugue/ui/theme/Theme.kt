package id.application.sangugue.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController


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

@Composable
fun SanguTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> LightColors // You can define a dark color scheme later if needed
        else -> LightColors
    }

    // 👉 System UI Controller (status bar & nav bar)
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = colorScheme.background == White

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colorScheme.background,
            darkIcons = useDarkIcons
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}