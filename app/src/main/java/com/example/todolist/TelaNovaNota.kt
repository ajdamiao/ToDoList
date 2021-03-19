package com.example.todolist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolist.databinding.ActivityNovaNotaBinding

class TelaNovaNota : AppCompatActivity() {
    private lateinit var binding: ActivityNovaNotaBinding

    private var lembrete: String? = null
    private var data: String? = null
    private var hora: String? = null

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
        val preferenciaNota = PreferenciaData(applicationContext) //aqui
        val preferenciaHora = PreferenciaHora(applicationContext)
        val botaoSalvar = binding.floatingActionButton


        botaoSalvar.setOnClickListener {
            val lembreteRecuperado = binding.editContainer.editAnotacao.text.toString()
            val dataRecuperado = binding.editContainer.editData.text.toString() //aqui
            val horaRecuperada = binding.editContainer.editHora.text.toString()
            val posicao = intent.getIntExtra("posicao", -1)

            if(lembreteRecuperado == "")
            {
                Toast.makeText(this, "Digite seu lembrete...", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(posicao == -1)
            {
                preferencia.adicionarLembrete((lembreteRecuperado))
                preferenciaNota.adicionarData((dataRecuperado)) //aqui
                preferenciaHora.adicionarHora((horaRecuperada))
                Toast.makeText(this,"Anotação Salva com Sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }else if(posicao >= 0)
            {
                preferencia.editarLembrete(lembreteRecuperado,posicao)
                preferenciaNota.editarData(dataRecuperado,posicao) // aqui
                preferenciaHora.editarHora(horaRecuperada,posicao)
                Toast.makeText(this,"Anotacao salva com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        lembrete = intent.getStringExtra("lembretes")
        data = intent.getStringExtra("datas")
        hora = intent.getStringExtra("horas")

        if(lembrete != null)
        {
            binding.editContainer.editAnotacao.setText(lembrete)
            binding.editContainer.editData.setText(data)
            binding.editContainer.editHora.setText(hora)
        }




    }
}