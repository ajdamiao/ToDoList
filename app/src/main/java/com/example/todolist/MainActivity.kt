package com.example.todolist

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.LembreteListAdapter.LembreteListViewHolder
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ItemLembreteBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var lembretes: List<String>
    private lateinit var datas: List<String>
    private lateinit var horas: List<String>

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.recycler.layoutManager = LinearLayoutManager(this)

        binding.btnNovaNota.setOnClickListener { novoLembrete() }
    }
    private fun novoLembrete()
    {
        val intent = Intent(this, TelaNovaNota::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        lembretes = PreferenciaLembrete(applicationContext).RecuperarLembrete()
        datas = PreferenciaData(applicationContext).recuperarData()
        horas = PreferenciaHora(applicationContext).recuperarHora()
        binding.recycler.adapter = LembreteListAdapter(lembretes, datas, horas)
    }
}

class LembreteListAdapter(val lembretes: List<String>, val datas: List<String>, val horas: List<String>): RecyclerView.Adapter<LembreteListViewHolder>(){

    inner class LembreteListViewHolder(val binding: ItemLembreteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LembreteListViewHolder {
        val binding = ItemLembreteBinding.inflate(LayoutInflater.from(parent.context),parent ,false)
        return LembreteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LembreteListViewHolder, position: Int) {
        with(holder)
        {
            binding.textView.text = lembretes[position]
            binding.editData.text = datas[position] //nao funciona
            //binding.editData.text = "teste" // funciona
            binding.editHora.text = horas[position]
            binding.constraintLayout.setOnClickListener {
                val intent = Intent(binding.root.context, TelaNovaNota::class.java)
                intent.putExtra("lembretes", lembretes[position])
                intent.putExtra("posicao", position)
                intent.putExtra("horas", horas[position])
                intent.putExtra("datas", datas[position])
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = lembretes.size
}