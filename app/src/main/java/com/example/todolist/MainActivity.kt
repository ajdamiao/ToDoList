package com.example.todolist

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ItemLembreteBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var lembretes: List<String>

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
        binding.recycler.adapter = LembreteListAdapter(lembretes)
    }




}


class LembreteListAdapter(val lembretes: List<String>): RecyclerView.Adapter<LembreteListAdapter.LembreteListViewHolder>(){


    inner class LembreteListViewHolder(val binding: ItemLembreteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LembreteListViewHolder {
        val binding = ItemLembreteBinding.inflate(LayoutInflater.from(parent.context),parent ,false)
        return LembreteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LembreteListViewHolder, position: Int) {
        with(holder)
        {
            binding.textView.text = lembretes[position]
            binding.constraintLayout.setOnClickListener {
                val intent = Intent(binding.root.context, TelaNovaNota::class.java)
                intent.putExtra("lembretes", lembretes[position])
                intent.putExtra("posicao", position)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = lembretes.size

}