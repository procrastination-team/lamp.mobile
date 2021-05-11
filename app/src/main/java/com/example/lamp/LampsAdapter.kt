package com.example.lamp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class LampsAdapter(
    private val lamps: List<Lamp>
) : RecyclerView.Adapter<LampsAdapter.LampViewHolder>() {
    class LampViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(lamp: Lamp) {
            root.findViewById<TextView>(R.id.textViewSign).text = lamp.name
            root.findViewById<TextView>(R.id.textView4).text = lamp.power.toString()

            val seekBar: SeekBar = root.findViewById(R.id.seekBar)

            seekBar.progress = lamp.power

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val text = root.findViewById<TextView>(R.id.textView4)
                    text.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar != null) {
                        lamp.power = seekBar.progress
                        Fragment()
                    }
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

    override fun onBindViewHolder(holder: LampViewHolder, position: Int) = holder.bind(lamps[position])

    override fun getItemCount() = lamps.size
}