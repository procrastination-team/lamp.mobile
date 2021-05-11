package com.example.lamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.myRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = LampsAdapter(List(10){Lamp("Lamp #$it", Random.nextInt(0,100))})
        }
    }
}