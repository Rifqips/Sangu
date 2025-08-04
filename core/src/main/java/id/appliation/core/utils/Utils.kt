package id.appliation.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


object Utils {


    fun Int.formatCurrency(): String {
        return String.format("%,d", this).replace(',', '.')
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun String.toFormattedIndoDate(): String {
        return try {
            OffsetDateTime.parse(this)
                .toLocalDate()
                .format(
                    DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("id", "ID"))
                )
        } catch (e: Exception) {
            this // fallback: kalau format gagal, return string as is
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(formatter)
    }

    fun formatRupiah(input: String): String {
        val number = input.toLongOrNull() ?: return "Rp0"
        val formatter = NumberFormat.getInstance(Locale("in", "ID"))
        return "Rp${formatter.format(number)}"
    }


}