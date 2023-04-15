package com.example.tp_projeckt.data.token

interface TokenDataSource {

	suspend fun get(): TokenModel

	suspend fun edit(data: TokenModel)
}