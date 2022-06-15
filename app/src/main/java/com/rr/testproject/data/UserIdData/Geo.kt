package com.rr.testproject.data.UserIdData

import com.google.gson.annotations.SerializedName

data class Geo(@SerializedName("lng")
               val lng: String = "",
               @SerializedName("lat")
               val lat: String = "")