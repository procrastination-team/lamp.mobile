package com.example.lamp

import com.squareup.moshi.Json

data class Lamp(
    val id: String,
    val name: String,
    var brightness: Int = 0,
    @Json(name = "power") var isOn: Boolean
)