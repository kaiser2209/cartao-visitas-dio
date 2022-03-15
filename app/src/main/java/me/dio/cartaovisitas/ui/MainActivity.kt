package me.dio.cartaovisitas.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import me.dio.cartaovisitas.App
import me.dio.cartaovisitas.R
import me.dio.cartaovisitas.databinding.ActivityMainBinding
import me.dio.cartaovisitas.util.Image

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListeners()
    }

    private fun insertListeners() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }

        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }

        adapter.listenerDelete = { businessCard ->
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.confirm_dialog_delete_card)
                .setPositiveButton(R.string.confirm_delete, DialogInterface.OnClickListener { _, _ ->
                    mainViewModel.delete(businessCard)
                    Toast.makeText(this, "Cartão Removido com Sucesso!", Toast.LENGTH_SHORT).show()
                })
                .setNegativeButton(R.string.cancel_delete, DialogInterface.OnClickListener { _, _ ->
                    Toast.makeText(this, "Ação Cancelada pelo Usuário!", Toast.LENGTH_SHORT).show()
                })
                .show()
        }

        adapter.listenerUpdate = { businessCard ->
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            intent.putExtra("businessCard", businessCard)
            startActivity(intent)
        }
    }

    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this) { businessCards ->
            adapter.submitList(businessCards)
        }
    }
}