package com.example.lovecalculator.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "history")
data class LoveModel(

    @SerializedName("fname")
    val firstName: String,

    @SerializedName("sname")
    val secondName: String,

    @SerializedName("percentage")
    val percentage: String,

    @SerializedName("result")
    val result: String,

    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    ) : Serializable