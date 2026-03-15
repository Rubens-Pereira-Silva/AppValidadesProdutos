package com.example.cadastrarprodutos

import android.content.Context
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlin.collections.mutableListOf

object PrefsHelper {

    fun salvarLista(context: Context, lista: List<List<String>>) {
        val sharedPref =  context.getSharedPreferences("listaProdutos", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(lista)
        editor.putString("lista", json)
        editor.apply()
    }

    fun carregarLista(context: Context): MutableList<List<String>> {
        val sharedPref = context.getSharedPreferences("listaProdutos", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPref.getString("lista", null)

        return if (json != null) {
            val type = object : TypeToken<MutableList<List<String>>>() {}.type
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
    }



}