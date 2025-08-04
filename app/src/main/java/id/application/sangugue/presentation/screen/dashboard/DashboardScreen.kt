package id.application.sangugue.presentation.screen.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.appliation.core.theme.GrayText
import id.appliation.core.theme.PLNBlue
import id.appliation.core.theme.PLNBlueDark
import id.appliation.core.theme.PLNSkyBlue
import id.appliation.core.utils.UiState
import id.appliation.core.utils.Utils.formatCurrency
import id.application.domain.model.transaction.ItemSummaryTransaction
import id.application.sangugue.presentation.screen.amount.TransactionList
import id.application.sangugue.presentation.screen.customview.DatePickerDashboard
import id.application.sangugue.presentation.screen.customview.rememberDatePickerDialog
import id.application.sangugue.presentation.viewmodel.dashboard.DashboardViewModel
import id.application.sangugue.presentation.viewmodel.transaction.TransactionViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    var startDate by remember { mutableStateOf(LocalDate.now().withDayOfMonth(1)) }
    var endDate by remember { mutableStateOf(LocalDate.now()) }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Konfirmasi Logout") },
            text = { Text("Apakah Anda yakin ingin keluar?") },
            confirmButton = {
                TextButton(onClick = {
                    showLogoutDialog = false
                    navController.navigate("login") {
                        viewModel.logout()
                        popUpTo(0)
                    }
                }) {
                    Text("Keluar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.getTransactions(
            startDate = startDate.toString(),
            endDate = endDate.toString()
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("input_amount") },
                containerColor = PLNBlueDark,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier
                    .size(72.dp)
                    .shadow(8.dp, CircleShape)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah", modifier = Modifier.size(32.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            val email by viewModel.userEmail.collectAsState()

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Halo, Selamat Datang!",
                        style = MaterialTheme.typography.titleLarge,
                        color = PLNBlueDark
                    )
                    Text(
                        text = email ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        color = GrayText
                    )
                }

                IconButton(onClick = { showLogoutDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout",
                        tint = Color.Red
                    )
                }
            }
            val transactionState by viewModel.getTransactionState.collectAsState()
            val data = (transactionState as? UiState.Success)?.data?.data

            data?.summary?.let {
                SummaryCarousel(it)
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Transaksi Terakhir",
                    style = MaterialTheme.typography.titleMedium,
                    color = PLNBlueDark
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    val startPickerDialog = rememberDatePickerDialog(startDate) {
                        startDate = it
                        viewModel.getTransactions(startDate.toString(), endDate.toString())
                    }
                    val endPickerDialog = rememberDatePickerDialog(endDate) {
                        endDate = it
                        viewModel.getTransactions(startDate.toString(), endDate.toString())
                    }

                    DatePickerDashboard(
                        date = startDate,
                        onClick = { startPickerDialog.show() },
                        modifier = Modifier
                    )

                    DatePickerDashboard(
                        date = endDate,
                        onClick = { endPickerDialog.show() },
                        modifier = Modifier
                    )
                }
            }

            when (transactionState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is UiState.Error -> {
                    Text(
                        "Gagal memuat ringkasan",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                is UiState.Success -> {
                    data?.details?.let { detailList ->
                        TransactionList(
                            transactions = detailList.filterNotNull().take(5),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                else -> Unit
            }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryCarousel(summary: ItemSummaryTransaction) {
    val items = remember {
        listOf(
            Triple("Saldo", summary.balance ?: 0, PLNBlueDark),
            Triple("Pemasukan", summary.income ?: 0, PLNBlue),
            Triple("Pengeluaran", summary.expense ?: 0, PLNSkyBlue)
        )
    }

    HorizontalUncontainedCarousel(
        state = rememberCarouselState { items.size },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        itemWidth = 240.dp,
        itemSpacing = 12.dp,
    ) { index ->
        val (title, amount, color) = items[index]
        SummaryCard(title = title, amount = amount, backgroundColor = color)
    }
}

@Composable
fun SummaryCard(
    title: String,
    amount: Int,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .height(140.dp)
            .clip(MaterialTheme.shapes.large)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
            Text(
                text = "Rp ${amount.formatCurrency()}",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }
    }
}



