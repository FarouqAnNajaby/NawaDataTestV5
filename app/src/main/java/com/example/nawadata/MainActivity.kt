package com.example.nawadata

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nawadata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listVocal = arrayListOf<String>()
    private val listKonsonan = arrayListOf<String>()
    private val listMember = arrayListOf<Int>()

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
                        listKonsonan.add(char.toString())
                    }
                }
                val dataVocal = convertToString(listVocal)
                val dataKonsonan = convertToString(listKonsonan)
                binding.tvHasilVokal.text = " $dataVocal"
                binding.tvHasilKonsonan.text = " $dataKonsonan"
            }
            else{
                Toast.makeText(this, "Please insert word", Toast.LENGTH_SHORT).show()
            }
            binding.buttonHasil.isEnabled = false
        }

        binding.buttonReset.setOnClickListener {
            listVocal.clear()
            listKonsonan.clear()
            binding.edtSoal1.setText("")
            binding.tvHasilKonsonan.text = ""
            binding.tvHasilVokal.text = ""
            binding.buttonHasil.isEnabled = true
        }

        binding.buttonHasil2.setOnClickListener {
            val dataCountFamily = binding.edtCountFamily.text
            val dataCountMember = binding.edtMember.text
            if (dataCountFamily?.isNotBlank() == true && dataCountMember?.isNotBlank() == true){
                val countFamilies = dataCountFamily.toString().toInt()
                val countMember = dataCountMember.toString().replace("\\s".toRegex(), "").length
                if (countFamilies != countMember){
                    Toast.makeText(this, "Input must be equal with count of family", Toast.LENGTH_SHORT).show()
                }
                else{
                    val data = binding.edtMember.text.toString().replace("\\s".toRegex(), "")
                    data.forEach {
                        listMember.add(it.toString().toInt())
                    }
                    val sum = listMember.sum()
                    val maxPassenger = 4
                    var totalBus = sum / maxPassenger
                    if (sum % maxPassenger != 0){
                        totalBus += 1
                    }
                    Toast.makeText(this, totalBus.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            binding.buttonHasil2.isEnabled = false
        }

        binding.buttonReset2.setOnClickListener {
            listMember.clear()
            binding.edtMember.setText("")
            binding.edtCountFamily.setText("")
            binding.buttonHasil2.isEnabled = true
        }

    }

    private fun convertToString(arrayList: ArrayList<String>): String {
        val data = arrayList.groupBy { it.first().lowercase() }
        val list = arrayListOf<String>()
        data.forEach { (_, u) ->
            u.forEach {
                list.add(it)
            }
        }
        return list.joinToString("").replace("\\s".toRegex(), "").lowercase()
    }
}