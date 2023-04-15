package com.example.tp_projeckt.data.token

import com.example.tp_projeckt.domain.ErrorType

class TokenModelConverter {

	fun convert(from: TokenModel): ErrorType = ErrorType(from.errorType)
}