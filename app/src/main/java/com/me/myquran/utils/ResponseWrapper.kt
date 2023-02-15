package com.me.myquran.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WrappedListResponse<T>(

	@field:SerializedName("code")
	@field:Expose
	val code: Int = 0,

	@field:SerializedName("message")
	@field:Expose
	val previous: String? = null,

	@field:SerializedName("data")
	@field:Expose
	val data: List<T>? = null
)

data class WrappedResponse<T>(

	@field:SerializedName("code")
	@field:Expose
	val code: Int = 0,

	@field:SerializedName("message")
	@field:Expose
	val previous: String? = null,

	@field:SerializedName("data")
	@field:Expose
	val data: T? = null
)