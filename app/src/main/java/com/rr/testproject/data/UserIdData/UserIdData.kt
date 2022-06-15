package com.rr.testproject.data.UserIdData

import com.google.gson.annotations.SerializedName

data class UserIdData(@SerializedName("website")
                      val website: String = "",
                      @SerializedName("address")
                      val address: Address,
                      @SerializedName("phone")
                      val phone: String = "",
                      @SerializedName("name")
                      val name: String = "",
                      @SerializedName("company")
                      val company: Company,
                      @SerializedName("id")
                      val id: Int = 0,
                      @SerializedName("email")
                      val email: String = "",
                      @SerializedName("username")
                      val username: String = "")