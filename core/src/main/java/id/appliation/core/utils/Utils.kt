package id.appliation.core.utils



object Utils {


    fun Int.formatCurrency(): String {
        return String.format("%,d", this).replace(',', '.')
    }



}