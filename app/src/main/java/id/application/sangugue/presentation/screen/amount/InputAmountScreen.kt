package id.application.sangugue.presentation.screen.amount

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.appliation.core.utils.UiState
import id.appliation.core.utils.Utils.formatDate
import id.application.domain.model.transaction.ItemRequestTransaction
import id.application.sangugue.presentation.screen.customview.rememberDatePickerDialog
import id.application.sangugue.presentation.viewmodel.transaction.TransactionViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputAmountScreen(
    navController: NavHostController,
    viewModel: TransactionViewModel = hiltViewModel()
) {

    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Pemasukan") }
    var option by remember { mutableStateOf("Jenis") }
    var description by remember { mutableStateOf("") }
    var transactionDate by remember { mutableStateOf(LocalDate.now()) }
    val datePickerDialog = rememberDatePickerDialog(transactionDate) {
        transactionDate = it
    }


    val transactionState = viewModel.transactionState

    LaunchedEffect(transactionState){
        when(val state = transactionState){
            is UiState.Success<*> -> {
                navController.navigateUp()
                viewModel.resetState()
            }
            is UiState.Error -> {
                Log.d("InputAmountScreen", "Error: ${state.message}")
            }
            else -> Unit
        }
    }


    InputAmount(
        amount = amount,
        option = option,
        category = category,
        description = description,
        date = transactionDate,
        onAmountChange = { amount = it },
        onOptionSelected = { option = it },
        onCategorySelected = { category = it },
        onDescriptionChange = { description = it },
        onDateClick = { datePickerDialog.show() },
        onInvestClick = {
            val request = ItemRequestTransaction(
                amount = amount.toInt(),
                category = category,
                description = description,
                transactionDate = formatDate(transactionDate),
                type = option
            )
            viewModel.createTransaction(request)
        },
        onKeypadPress = { key -> amount = handleKeypadPress(amount, key) },
        navHostController = navController
    )

}
