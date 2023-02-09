package com.example.sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitedatabase.Database.DataBase
import com.example.sqlitedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var dataBase: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase = DataBase(applicationContext)

        binding.btnAdd.setOnClickListener(){

            dataBase.addData(
            binding.edtName.text.toString(),
            binding.edtSurname.text.toString(),
            binding.edtAddress.text.toString()
            )
        }

        binding.btnShow.setOnClickListener(){

            val list = dataBase.showData()
            binding.recycle.layoutManager = LinearLayoutManager(applicationContext)
            binding.recycle.adapter = RecycleAdapter(list)

        }
    }
}