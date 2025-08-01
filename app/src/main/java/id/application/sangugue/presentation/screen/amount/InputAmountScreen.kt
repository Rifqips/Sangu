package id.application.sangugue.presentation.screen.amount

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

@Composable
fun InputAmountScreen(navController: NavHostController) {

    var amount by remember { mutableStateOf("") }
    var option by remember { mutableStateOf("Jenis") }
    var category by remember { mutableStateOf("Pemasukan") }
    var description by remember { mutableStateOf("") }

    InputAmount(
        amount = amount,
        option = option,
        category = category,
        description = description,
        onAmountChange = { amount = it },
        onOptionSelected = { option = it },
        onCategorySelected = { category = it },
        onDescriptionChange = { description = it },
        onInvestClick = {
            Log.d("Input", "Nominal: $amount, Kategori: $category, Deskripsi: $description")
            // simpan ke database atau navigasi
        },
        onKeypadPress = { key ->
            amount = when (key) {
                "⌫" -> amount.dropLast(1)
                "." -> if (!amount.contains(".")) amount + "." else amount
                else -> amount + key
            }
        },
        navHostController = navController
    )
}
