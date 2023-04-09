package com.example.tp_projeckt.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

private const val BASE_URL = "http://192.168.0.104:8080"

val dataModule = module {

    single<Moshi> {
        Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .baseUrl(BASE_URL)
            .client(get())
            .build()
    }
}