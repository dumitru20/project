package com.example.tp_projeckt.data

import com.example.tp_projeckt.data.token.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor(
	private val tokenDataSource: TokenDataSource
) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
		val original: Request = chain.request()

		val token = tokenDataSource.get().token

		val request: Request = original.newBuilder()
			.header("authorization","Bearer $token")
			.build()
		chain.proceed(request)
	}
}