package id.application.sangugue.presentation.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.application.sangugue.data.model.transaction.TransactionList
import id.application.sangugue.data.model.transaction.sampleTransactions
import id.application.sangugue.ui.theme.GrayText
import id.application.sangugue.ui.theme.PLNBlueDark
import id.application.sangugue.ui.theme.White
import id.application.sangugue.utils.Utils.SetSystemBarColor

@Composable
fun DashboardScreen(navController: NavHostController) {
    SetSystemBarColor(color = White, darkIcons = true)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Halo, Selamat Datang!",
            style = MaterialTheme.typography.titleLarge,
            color = PLNBlueDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Total Saldo Anda",
            style = MaterialTheme.typography.bodyMedium,
            color = GrayText
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = PLNBlueDark),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Rp 12.000.000",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
