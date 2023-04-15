package com.example.tp_projeckt.data.token

interface TokenDataStore {

	suspend fun get(): TokenModel

	suspend fun edit(data: TokenModel)
}