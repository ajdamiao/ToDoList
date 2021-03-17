package com.example.todolist.model

data class Lembretes(
    var nota: String
)

class LembretesBuilder{
    var nota: String = ""

    fun build(): Lembretes = Lembretes(nota)
}


fun lembretes(block: LembretesBuilder.() -> Unit): Lembretes = LembretesBuilder().apply(block).build()

fun adicionarLembrete(): MutableList<Lembretes> = mutableListOf(
    lembretes {
        nota = "teste 1"
    },
    lembretes {
        nota = "teste 2"
    },
    lembretes {
        nota = "teste 3"
    },
    lembretes {
        nota = "teste 4"
    },
)

