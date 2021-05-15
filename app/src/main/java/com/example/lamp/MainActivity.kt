package com.example.lamp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.ResponseBody
import retrofit2.*

class MainActivity : AppCompatActivity() {
    private val lampAPI = LampAPI.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.progres_bar)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = ProgressBar.VISIBLE
        progressBar.visibility = ProgressBar.INVISIBLE

        setContentView(R.layout.activity_main)

        lampAPI.getLamps().enqueue(object : Callback<List<Lamp>> {
            override fun onResponse(call: Call<List<Lamp>>, response: Response<List<Lamp>>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    findViewById<RecyclerView>(R.id.myRecyclerView).apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = LampsAdapter(responseBody)
                    }
                } else {
                    Log.e("Error", "Error responseBody = null")
                }
            }

            override fun onFailure(call: Call<List<Lamp>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })

    }
}