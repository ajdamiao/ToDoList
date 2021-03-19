package com.example.todolist

import android.content.Context
import android.content.SharedPreferences

class PreferenciaData(private val context: Context) {

    private val ARQUIVO = "data.preferencia"
    private val CHAVE = "nome"
    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    fun adicionarData(dataAnotacao: String)
    {

        val data = preferences.getString(CHAVE, "")

        val novaLista = when{
            data.isNullOrEmpty() -> dataAnotacao
            else -> "$data;$dataAnotacao"
        }

        editor.putString(CHAVE, novaLista)
        editor.commit()
    }

        fun editarData(dataAnotacao: String, position: Int)
        {
            val data = preferences.getString(CHAVE, "")
            val lista = data!!.split(";").toMutableList()

            lista[position] = dataAnotacao

            val notaLista = lista.joinToString(separator = ";"){ it -> "$it" }

            editor.putString(CHAVE, notaLista)
            editor.commit()
        }

    fun recuperarData(): List<String>{
        val datas = preferences.getString(CHAVE, "")
        val lista = datas?.split(";")
        return lista ?: ArrayList<String>()
    }

    init {
        preferences = context.getSharedPreferences(ARQUIVO, 0)
        editor = preferences.edit()
    }

}