package com.example.cadastrarprodutos

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cadastrarprodutos.ui.theme.CadastrarProdutosTheme
import com.example.cadastrarprodutos.ui.theme.components.ColunaProdutos
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.time.ExperimentalTime

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CadastrarProdutosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun App( modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var nomeProduce by remember { mutableStateOf("")
        }
    var dataProduce by remember { mutableStateOf("")
        }
    val listaProdutos = remember {mutableStateListOf<List<String>>().apply {
       addAll(PrefsHelper.carregarLista(context))
        }
    }

    var openDatePicker by remember {
        mutableStateOf(false)
    }
    val state = rememberDatePickerState()

    fun excluirProduto(index: Int){
        listaProdutos.removeAt(index)
        PrefsHelper.salvarLista(context, listaProdutos)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texto do topo da tela
        Text(
            text = "Produtos",
            modifier = modifier,
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
                modifier = Modifier.width(160.dp)
            )

            // input da data de validade
            TextField(
                dataProduce,
                onValueChange = {},
                modifier = Modifier.width(160.dp),
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
                if (nomeProduce != "" && dataProduce != "") {
                    val sublista = listOf(nomeProduce, dataProduce)
                    listaProdutos.add(sublista)

                    PrefsHelper.salvarLista(context, listaProdutos)

                    nomeProduce = ""
                    dataProduce = ""
                }

            }),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Cadastrar"
            )
        }
        ColunaProdutos(listaProdutos) {
            idx -> excluirProduto(idx)
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, name = "Message Card Preview")
@Composable
fun PreviewMessageCard() {
    App() // Calls the actual composable with sample data
}

