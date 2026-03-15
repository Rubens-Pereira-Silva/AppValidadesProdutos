package com.example.cadastrarprodutos.ui.theme.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cadastrarprodutos.ui.theme.utils.corDeFundo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProdutoCard(nome: String, data: String, onClick: () -> Unit){

    fun diasRestantes(): Int {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val convertData = LocalDate.parse(data, formatter)

        val data1 = LocalDate.of(convertData.year, convertData.monthValue, convertData.dayOfMonth)
        val data2 = LocalDate.now()

        return (data1.toEpochDay() - data2.toEpochDay()).toInt()
    }

    val diasRestantes = diasRestantes()

    val corDeFundo: Color = corDeFundo(diasRestantes)


    Column(
        modifier = Modifier
            .border(width = 1.dp, color = corDeFundo)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.width(300.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = nome,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = data
            )
        }

        Spacer(
            modifier = Modifier.height(5.dp)
        )


        Text(
            text =  if(diasRestantes <= 0) "VENCIDO" else "$diasRestantes dias restantes",
            modifier = Modifier.padding(10.dp)
        )

        Button(
            onClick = { onClick() },
            modifier = Modifier.width(150.dp),
        ) {
            Text(
                text = "Excluir"
            )
        }
    }
    Spacer(
        modifier = Modifier.height(15.dp)
    )
}
