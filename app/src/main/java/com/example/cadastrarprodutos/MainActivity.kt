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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.cadastrarprodutos.PrefsHelper.salvarLista
import com.example.cadastrarprodutos.ui.theme.CadastrarProdutosTheme
import com.example.cadastrarprodutos.ui.theme.components.BTNsCadastro
import com.example.cadastrarprodutos.ui.theme.components.ColunaProdutos
import com.example.cadastrarprodutos.ui.theme.utils.rememberWindowInfo
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
    val windowInfo = rememberWindowInfo()
    val context = LocalContext.current

    val listaProdutos = remember {mutableStateListOf<List<String>>().apply {
       addAll(PrefsHelper.carregarLista(context))
        }
    }

    fun addProduto(nome: String, data: String){
        if(nome != "" && data != ""){
            val sublista = listOf(nome, data)
            listaProdutos.add(sublista)
            salvarLista(context, listaProdutos)

        }
    }

    fun excluirProduto(index: Int){
        listaProdutos.removeAt(index)
        PrefsHelper.salvarLista(context, listaProdutos)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BTNsCadastro({
            nome, data -> addProduto(nome, data)
        })

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

