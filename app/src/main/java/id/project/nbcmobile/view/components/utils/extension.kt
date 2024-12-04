package id.project.nbcmobile.view.components.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.formatDate() : String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return try {
        val date = inputFormat.parse(this) as Date
        outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        this // Return original date string if parsing fails
    }
}

fun Int.formatToRupiah() : String {
    val locale = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(locale)
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(this).replace("Rp", "Rp. ")
}