package com.example.cadastrarprodutos.ui.theme.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cadastrarprodutos.ui.theme.utils.rememberWindowInfo
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BTNsCadastro(onClick: (String, String) -> Unit){
    val windowInfo = rememberWindowInfo()

    var nomeProduce by remember { mutableStateOf("")
    }
    var dataProduce by remember { mutableStateOf("")
    }

    var openDatePicker by remember {
        mutableStateOf(false)
    }
    val state = rememberDatePickerState()

    // Texto do topo da tela
    Text(
        text = "Produtos",
        modifier = Modifier,
        style = MaterialTheme.typography.titleLarge
    )
    // inputs para cadastrar produtos
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // input do nome do produto
        TextField(
            value = nomeProduce,
            onValueChange = { newText ->
                nomeProduce = newText
            },
            label = { Text("Produto") },
            modifier = Modifier.width(windowInfo.screenWidth * 0.4f)
        )

        // input da data de validade
        TextField(
            dataProduce,
            onValueChange = {},
            modifier = Modifier.width(windowInfo.screenWidth * 0.4f),
            label = { Text("Data") },
            interactionSource = remember {
                MutableInteractionSource()
            }.also {
                LaunchedEffect(it) {
                    it.interactions.collectLatest { interaction ->
                        if (interaction is PressInteraction.Release) {
                            openDatePicker = true
                        }
                    }
                }
            },
            readOnly = true

        )
    }

    AnimatedVisibility(openDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                openDatePicker = false
            }, confirmButton = {
                Button(onClick = {
                    //fazer a conveção para o campo de data
                    state.selectedDateMillis?.let { millis ->
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        sdf.timeZone = TimeZone.getTimeZone("UTC")
                        dataProduce = sdf.format(Date(millis))
                    }
                    openDatePicker = false
                }) {
                    Text("Selecionar")
                }
            }

        ) {
            DatePicker(state)
        }

    }


    // botão para cadastrar produtos
    Button(
        onClick = ({
            onClick(nomeProduce, dataProduce)
        }),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Cadastrar"
        )
    }
}