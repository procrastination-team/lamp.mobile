package com.example.lamp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LampsAdapter(
    private val lamps: List<Lamp>
) : RecyclerView.Adapter<LampsAdapter.LampViewHolder>() {
    class LampViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(lamp: Lamp) {
            root.findViewById<TextView>(R.id.textViewLampID).text = lamp.id
            root.findViewById<TextView>(R.id.textViewLampBrightness).text =
                lamp.brightness.toString()
            val image = root.findViewById<ImageView>(R.id.OnOff)
            if (lamp.power == 0) {
                image.setImageResource(android.R.drawable.button_onoff_indicator_off)
            } else {
                image.setImageResource(android.R.drawable.button_onoff_indicator_on)
            }

            val seekBar: SeekBar = root.findViewById(R.id.seekBar)

            seekBar.progress = lamp.brightness

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val text = root.findViewById<TextView>(R.id.textViewLampBrightness)
                    text.text = progress.toString()
                    lamp.brightness = seekBar.progress
                    lightChange(lamp)

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

        }

        private fun lightChange(lamp: Lamp) {
            LampAPI.newInstance().putLamp(lamp.id, lamp).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.i("Adapter", "post successful")
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("Error", t.message.toString())
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LampViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: LampViewHolder, position: Int) =
        holder.bind(lamps[position])

    override fun getItemCount() = lamps.size
}