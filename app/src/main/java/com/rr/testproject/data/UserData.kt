package com.rr.testproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "quotesTable")
data class UserData(

    @SerializedName("id")
    val id:String,

    @SerializedName("title")
    val title:String,

    @SerializedName("body")
    val body:String,
){
    @PrimaryKey(autoGenerate = true)
    var user=0
}
