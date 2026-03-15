package com.example.cadastrarprodutos.ui.theme.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ColunaProdutos(listaProdutos: MutableList<List<String>> , onClick: (Int) -> Unit){
    // Coluna onde é mostrado os produtos cadastrados
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ){
        for((index, produto) in listaProdutos.withIndex()){
            ProdutoCard(produto[0],
                produto[1],
                { onClick(index) }
            )
        }
    }
}
