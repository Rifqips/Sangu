package id.application.sangugue.presentation.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.application.sangugue.data.model.transaction.TransactionList
import id.application.sangugue.data.model.transaction.sampleTransactions
import id.application.sangugue.ui.theme.GrayText
import id.application.sangugue.ui.theme.PLNBlueDark
import id.application.sangugue.ui.theme.White
import id.application.sangugue.utils.Utils.SetSystemBarColor
import id.application.sangugue.utils.Utils.formatCurrency

@Composable
fun DashboardScreen(navController: NavHostController) {
    SetSystemBarColor(color = White, darkIcons = true)

    var showLogoutDialog by remember { mutableStateOf(false) }

    val totalSaldo = 12_000_000
    val totalPemasukan = 8_000_000
    val totalPengeluaran = 4_000_000

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Konfirmasi Logout") },
            text = { Text("Apakah Anda yakin ingin keluar?") },
            confirmButton = {
                TextButton(onClick = {
                    showLogoutDialog = false
                    navController.navigate("login") {
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
                        text = "Rifqi Padi Siliwangi",
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    InfoCard(title = "Saldo", amount = totalSaldo, backgroundColor = PLNBlueDark)
                }
                Box(modifier = Modifier.weight(1f)) {
                    InfoCard(title = "Pemasukan", amount = totalPemasukan, backgroundColor = Color(0xFF4CAF50))
                }
                Box(modifier = Modifier.weight(1f)) {
                    InfoCard(title = "Pengeluaran", amount = totalPengeluaran, backgroundColor = Color(0xFFF44336))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Transaksi Terakhir",
                style = MaterialTheme.typography.titleMedium,
                color = PLNBlueDark
            )

            TransactionList(
                transactions = sampleTransactions.take(5),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}



@Composable
fun InfoCard(title: String, amount: Int, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
            Text(
                text = "Rp ${amount.formatCurrency()}",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
    }
}



