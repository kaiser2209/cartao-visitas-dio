package me.dio.cartaovisitas.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import me.dio.cartaovisitas.App
import me.dio.cartaovisitas.R
import me.dio.cartaovisitas.data.BusinessCard
import me.dio.cartaovisitas.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private var businessCard: BusinessCard? = null
    private var backgroundColor: String? = "#FFFFFF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        businessCard = intent.getParcelableExtra<BusinessCard>("businessCard")
        if (businessCard != null) loadCard()

        insertListeners()
    }

    private fun insertListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnConfirm.setOnClickListener {
            if (businessCard == null) {
                val businessCard = BusinessCard(
                    nome = binding.tilNome.editText?.text.toString(),
                    empresa = binding.tilEmpresa.editText?.text.toString(),
                    telefone = binding.tilTelefone.editText?.text.toString(),
                    email = binding.tilEmail.editText?.text.toString(),
                    fundoPersonalizado = backgroundColor
                )

                mainViewModel.insert(businessCard)
                Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            } else {
                val businessCard = businessCard?.copy(
                    nome = binding.tilNome.editText?.text.toString(),
                    empresa = binding.tilEmpresa.editText?.text.toString(),
                    telefone = binding.tilTelefone.editText?.text.toString(),
                    email = binding.tilEmail.editText?.text.toString(),
                    fundoPersonalizado = backgroundColor
                )

                businessCard?.let { businessCard -> mainViewModel.update(businessCard) }
                Toast.makeText(this, "Cartão Atualizado com Sucesso!", Toast.LENGTH_SHORT).show()

            }
            finish()
        }

        binding.tilCor.editText?.setOnClickListener {

            ColorPickerDialog.Builder(this@AddBusinessCardActivity)
                .setTitle("Escolha a Cor")
                .setColorShape(ColorShape.CIRCLE)
                .setColorListener { color, colorHex ->
                    it.setBackgroundColor(color)
                    backgroundColor = colorHex.uppercase()
                }
                .show()

        }
    }

    private fun loadCard() {
        binding.tilNome.editText?.setText(businessCard?.nome)
        binding.tilEmpresa.editText?.setText(businessCard?.empresa)
        binding.tilEmail.editText?.setText(businessCard?.email)
        binding.tilTelefone.editText?.setText(businessCard?.telefone)
        backgroundColor = businessCard?.fundoPersonalizado
        binding.tilCor.editText?.setBackgroundColor(Color.parseColor(backgroundColor))
    }
}

