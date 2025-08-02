package id.application.sangugue.utils


import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

object Utils {


    @Composable
    fun SetSystemBarColor(
        color: Color,
        darkIcons: Boolean = true,
        navigationBarColor: Color = color
    ) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color,
                darkIcons = darkIcons
            )
            systemUiController.setNavigationBarColor(
                color = navigationBarColor,
                darkIcons = darkIcons
            )
        }
    }

    fun Int.formatCurrency(): String {
        return String.format("%,d", this).replace(',', '.')
    }



}