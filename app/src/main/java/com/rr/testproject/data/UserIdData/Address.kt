package com.rr.testproject.data.UserIdData

import com.google.gson.annotations.SerializedName

data class Address(@SerializedName("zipcode")
                   val zipcode: String = "",
                   @SerializedName("geo")
                   val geo: Geo,
                   @SerializedName("suite")
                   val suite: String = "",
                   @SerializedName("city")
                   val city: String = "",
                   @SerializedName("street")
                   val street: String = "")