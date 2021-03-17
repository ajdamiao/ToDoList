package com.example.todolist

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolist.databinding.ActivityNovaNotaBinding
import kotlin.system.measureNanoTime

class TelaNovaNota : AppCompatActivity() {
    private lateinit var binding: ActivityNovaNotaBinding

    private var nota: String? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNovaNotaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val toolbar = binding.toolbarNovaNota
        toolbar.setTitle("Novo Lembrete")
        toolbar.setTitleTextColor(R.color.white)


        val preferencia = PreferenciaLembrete(applicationContext)
        val botaoSalvar = binding.floatingActionButton

        botaoSalvar.setOnClickListener {
            val lembreteRecuperado = binding.editContainer.editAnotacao.text.toString()
            val posicao = intent.getIntExtra("posicao", -1)

            if(lembreteRecuperado == "")
            {
                Toast.makeText(this, "Digite seu lembrete...", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(posicao == -1)
            {
                preferencia.adicionarLembrete((lembreteRecuperado))
                Toast.makeText(this,"Anotação Salva com Sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }else if(posicao >= 0)
            {
                preferencia.editarLembrete(lembreteRecuperado,posicao)
                Toast.makeText(this,"Anotacao salva com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }

        }

        nota = intent.getStringExtra("notas")

        if(nota != null)
        {
            binding.editContainer.editAnotacao.setText(nota)
        }

    }
}