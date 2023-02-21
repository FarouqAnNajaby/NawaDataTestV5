package com.example.nawadata

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nawadata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listVocal = arrayListOf<String>()
    private val filterListVocal = arrayListOf<String>()
    private val listKonsonan = arrayListOf<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonHasil.setOnClickListener {
            val data = binding.edtSoal1.text
            if (data?.isNotBlank() == true){
                data.forEach { char ->
                    if (char == 'a' || char == 'i'|| char == 'u' || char == 'e' || char == '0'){
                        listVocal.add(char.toString())
                    }
                    else{
                        listKonsonan.add(char)
                    }
                }
                val dataVocal = convertToString(listVocal)
                binding.tvHasilVokal.text = " $dataVocal"
            }
            else{
                Toast.makeText(this, "Please insert word", Toast.LENGTH_SHORT).show()
            }
            binding.buttonHasil.isEnabled = false
        }

        binding.buttonReset.setOnClickListener {
            listVocal.clear()
            binding.edtSoal1.setText("")
            binding.tvHasilKonsonan.text = ""
            binding.tvHasilVokal.text = ""
            binding.buttonHasil.isEnabled = true
        }

    }

    private fun convertToString(arrayList: ArrayList<String>): String {
        var s = ""
        val data = arrayList.groupBy { it.first().lowercase() }
        val list = arrayListOf<String>()
        data.forEach { (_, u) ->
            u.forEach {
                list.add(it)
            }
        }
        s = list.joinToString("")
        return s
    }
}