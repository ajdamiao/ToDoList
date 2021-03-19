package com.example.todolist

import android.content.Context
import android.content.SharedPreferences

class PreferenciaHora(private val context: Context) {

    private val ARQUIVO = "hora.preferencia"
    private val CHAVE = "nome"
    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    fun adicionarHora(horaAnotacao: String)
    {

        val hora = preferences.getString(CHAVE,"")

        val novaLista = when{
            hora.isNullOrEmpty() -> horaAnotacao
            else -> "$hora;$horaAnotacao"
        }

        editor.putString(CHAVE, novaLista)
        editor.commit()
    }

    fun editarHora(horaAnotacao: String, position: Int)
    {
        val hora = preferences.getString(CHAVE,"")
        val lista = hora!!.split(";").toMutableList()

        lista[position] = horaAnotacao

        val notaLista = lista.joinToString(separator = ";"){it -> "$it"}

        editor.putString(CHAVE, notaLista)
        editor.commit()
    }

    fun recuperarHora(): List<String>{
        val horas = preferences.getString(CHAVE, "")
        val lista = horas?.split(";")
        return lista ?: ArrayList<String>()
    }

    init{
        preferences = context.getSharedPreferences(ARQUIVO, 0)
        editor = preferences.edit()
    }


}