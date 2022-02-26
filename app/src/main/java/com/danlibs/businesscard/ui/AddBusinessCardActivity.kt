package com.danlibs.businesscard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danlibs.businesscard.App
import com.danlibs.businesscard.R
import com.danlibs.businesscard.data.BusinessCard
import com.danlibs.businesscard.databinding.ActivityAddBusinessCardBinding
import yuku.ambilwarna.AmbilWarnaDialog


class AddBusinessCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBusinessCardBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBusinessCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.tilName.editText?.text.toString(),
                phone = binding.tilPhone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                company = binding.tilCompany.editText?.text.toString(),
                personalizedBackground = if (binding.tvColorCode.text != "") binding.tvColorCode.text.toString() else "-5592406"
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.show_success, Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnColor.setOnClickListener {
            val listener = object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {
                    return
                }

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    binding.previewSelectedColor.setBackgroundColor(color)
                    binding.tvColorCode.text = color.toString()
                }
            }
            val colorDialog = AmbilWarnaDialog(this, 0xAAAAAA, false, listener)
            colorDialog.show()
        }
    }
}