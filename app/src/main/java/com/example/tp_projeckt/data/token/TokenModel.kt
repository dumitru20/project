package com.example.tp_projeckt.data.token

import com.squareup.moshi.Json

data class TokenModel(
	@Json(name = "token") val token: String? = null,
	@Json(name = "errorType") val errorType: String? = null
)
