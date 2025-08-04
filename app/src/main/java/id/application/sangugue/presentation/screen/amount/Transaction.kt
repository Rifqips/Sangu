package id.application.sangugue.presentation.screen.amount

import android.os.Build
import androidx.annotation.RequiresApi
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
import id.appliation.core.theme.PurpleGrey40
import id.appliation.core.utils.Utils.formatRupiah
import id.appliation.core.utils.Utils.toFormattedIndoDate
import id.application.domain.model.transaction.ItemDetailTransaction

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionList(transactions: List<ItemDetailTransaction>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(transactions) { transaction ->
            TransactionItem(transaction)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionItem(transaction: ItemDetailTransaction) {
    val isIncome = transaction.type?.lowercase() == "income"
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
                    text = transaction.type ?: "-",
                    style = MaterialTheme.typography.bodyLarge,
                    color = GrayText
                )
                Text(
                    text = transaction.transactionDate?.toFormattedIndoDate() ?:"-",
                    style = MaterialTheme.typography.bodySmall,
                    color = PurpleGrey40
                )
            }

            Text(
                text = if (isIncome) "+Rp ${formatRupiah(transaction.amount.toString())}" else "-Rp ${-(transaction.amount ?: 0)}",
                style = MaterialTheme.typography.bodyLarge,
                color = color
            )
        }
    }
}
