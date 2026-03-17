package com.example.cadastrarprodutos.ui.theme.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cadastrarprodutos.ui.theme.utils.WindowInfo
import com.example.cadastrarprodutos.ui.theme.utils.rememberWindowInfo

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ColunaProdutos(listaProdutos: MutableList<List<String>> , onClick: (Int) -> Unit){
    val windowInfo = rememberWindowInfo()
    // Coluna onde é mostrado os produtos cadastrados
    if(windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact){
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

    }else if(windowInfo.screenWidthInfo == WindowInfo.WindowType.Medium){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            for(i in 0 until listaProdutos.size step 2){

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    ProdutoCard(listaProdutos[i][0],
                        listaProdutos[i][1],
                        { onClick(i) }
                    )
                    if(i+1 < listaProdutos.size){
                        Spacer(modifier = Modifier.width(20.dp))

                        ProdutoCard(listaProdutos[i+1][0],
                            listaProdutos[i+1][1],
                            { onClick(i+1) }
                        )
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

            }
        }
    }else{
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            for(i in 0 until listaProdutos.size step 4){

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    ProdutoCard(listaProdutos[i][0],
                        listaProdutos[i][1],
                        { onClick(i) }
                    )
                    if(i+1 < listaProdutos.size){
                        Spacer(modifier = Modifier.width(20.dp))

                        ProdutoCard(listaProdutos[i+1][0],
                            listaProdutos[i+1][1],
                            { onClick(i+1) }
                        )
                    }

                    if(i+2 < listaProdutos.size){
                        Spacer(modifier = Modifier.width(20.dp))

                        ProdutoCard(listaProdutos[i+2][0],
                            listaProdutos[i+2][1],
                            { onClick(i+2) }
                        )
                    }

                    if(i+3 < listaProdutos.size){
                        Spacer(modifier = Modifier.width(20.dp))

                        ProdutoCard(listaProdutos[i+3][0],
                            listaProdutos[i+3][1],
                            { onClick(i+3) }
                        )
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

            }
        }
    }
}
