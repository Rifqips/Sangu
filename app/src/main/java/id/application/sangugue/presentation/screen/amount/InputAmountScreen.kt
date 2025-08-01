package id.application.sangugue.presentation.screen.amount

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

@Composable
fun InputAmountScreen(navController: NavHostController) {

    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("New home") }

    InputAmount(
        amount = amount,
        category = category,
        onAmountChange = { amount = it },
        onCategoryEdit = { },
        onInvestClick = {  },
        navHostController = navController,
        onKeypadPress = { key ->
            when (key) {
                "⌫" -> amount = amount.dropLast(1)
                "." -> if (!amount.contains(".")) amount += "."
                else -> amount += key
            }
        }
    )
}
