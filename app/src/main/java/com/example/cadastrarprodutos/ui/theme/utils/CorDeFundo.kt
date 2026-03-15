package com.example.cadastrarprodutos.ui.theme.utils

import android.graphics.Color
import com.example.cadastrarprodutos.ui.theme.AMARELO
import com.example.cadastrarprodutos.ui.theme.PRETO
import com.example.cadastrarprodutos.ui.theme.VERDE
import com.example.cadastrarprodutos.ui.theme.VERMELHO

fun corDeFundo(diasRestantes: Int): androidx.compose.ui.graphics.Color {
    if(diasRestantes <= 0)
        return PRETO
    else if(diasRestantes <= 3)
        return VERMELHO
    else if(diasRestantes <= 7)
        return AMARELO
    else
        return VERDE
}
