package com.me.myquran.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AudioResponse(

	@field:SerializedName("01")
	@field:Expose
	val audio01: String? = null,

	@field:SerializedName("02")
	@field:Expose
	val audio02: String? = null,

	@field:SerializedName("03")
	@field:Expose
	val audio03: String? = null,

	@field:SerializedName("04")
	@field:Expose
	val audio04: String? = null,

	@field:SerializedName("05")
	@field:Expose
	val audio05: String? = null
)