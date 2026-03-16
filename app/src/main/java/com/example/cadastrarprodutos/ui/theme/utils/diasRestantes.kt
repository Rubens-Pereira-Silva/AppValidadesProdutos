package com.example.cadastrarprodutos.ui.theme.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun diasRestantes(data: String): Int {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val convertData = LocalDate.parse(data, formatter)

    val data1 = LocalDate.of(convertData.year, convertData.monthValue, convertData.dayOfMonth)
    val data2 = LocalDate.now()

    return (data1.toEpochDay() - data2.toEpochDay()).toInt()
}