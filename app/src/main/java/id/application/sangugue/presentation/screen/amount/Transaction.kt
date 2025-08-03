package id.application.sangugue.presentation.screen.amount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.appliation.core.theme.GrayText
import id.appliation.core.theme.LightGray

data class Transaction(
    val id: Int,
    val title: String,
    val amount: Double,
    val type: String,
    val date: String
)

val sampleTransactions = listOf(
    Transaction(1, "Gaji Bulanan", 8000000.0, "pemasukan", "30 Juli 2025"),
    Transaction(2, "Makan Siang", -25000.0, "pengeluaran", "30 Juli 2025"),
    Transaction(3, "Transport", -15000.0, "pengeluaran", "29 Juli 2025"),
    Transaction(4, "Freelance", 3000000.0, "pemasukan", "28 Juli 2025"),
    Transaction(5, "Belanja Online", -200000.0, "pengeluaran", "27 Juli 2025"),
)

@Composable
fun TransactionList(transactions: List<Transaction>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(transactions) { transaction ->
            TransactionItem(transaction)
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    val isIncome = transaction.type == "pemasukan"
    val color = if (isIncome) Color(0xFF4CAF50) else Color(0xFFF44336)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .background(LightGray)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isIncome) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = GrayText
                )
                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            Text(
                text = if (isIncome) "+Rp ${transaction.amount}" else "-Rp ${-transaction.amount}",
                style = MaterialTheme.typography.bodyLarge,
                color = color
            )
        }
    }
}
