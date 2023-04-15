package com.example.tp_projeckt.data.token

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class TokenDataStoreImpl(private var context: Context) : TokenDataStore {

	private val Context.dataStore by preferencesDataStore(name = "settings")

	companion object {

		val TOKEN = stringPreferencesKey("token")
	}

	override suspend fun get(): TokenModel = context.dataStore.data.catch { exception ->
		if (exception is IOException) {
			emit(emptyPreferences())
		} else {
			throw exception
		}
	}.map { preferences ->
		val token = preferences[TOKEN] ?: ""

		TokenModel(token)
	}.first()

	override suspend fun edit(data: TokenModel) {
		context.dataStore.edit { preferences ->
			if (data.token != null) {
				preferences[TOKEN] = data.token
			}
		}
	}
}
