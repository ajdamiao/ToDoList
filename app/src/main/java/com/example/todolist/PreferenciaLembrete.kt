package com.example.todolist

import android.content.Context
import android.content.SharedPreferences

class PreferenciaLembrete(private val context: Context) {

    private val ARQUIVO = "anotacao.preferencia"
    private val CHAVE = "nome"
    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    fun adicionarLembrete(anotacao: String)
    {
        val nota = preferences.getString(CHAVE, "")

        val novaLista = when{
            nota.isNullOrEmpty() -> anotacao
            else -> "$nota;$anotacao"
        }

        editor.putString(CHAVE, novaLista)
        editor.commit()
    }

    fun editarLembrete(anotacao: String, position: Int) {
        val nota = preferences.getString(CHAVE, "")
        val lista = nota!!.split(";").toMutableList()

        lista[position] = anotacao

        val notaLista = lista.joinToString(separator = ";") { it ->
            "$it"
        }
        editor.putString(CHAVE, notaLista)
        editor.commit()
    }



    fun RecuperarLembrete(): List<String>{
        val notas = preferences.getString(CHAVE, "")
        val lista = notas?.split(";")
        return lista ?: ArrayList<String>()
    }


    init{
        preferences = context.getSharedPreferences(ARQUIVO, 0)
        editor = preferences.edit()
    }

}
