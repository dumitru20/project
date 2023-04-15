package com.example.tp_projeckt.di

import com.example.tp_projeckt.data.HeaderInterceptor
import com.example.tp_projeckt.data.authorization.login.LoginApi
import com.example.tp_projeckt.data.authorization.login.LoginDataSource
import com.example.tp_projeckt.data.authorization.login.LoginDataSourceImpl
import com.example.tp_projeckt.data.authorization.login.LoginRepositoryImpl
import com.example.tp_projeckt.data.authorization.registration.*
import com.example.tp_projeckt.data.token.TokenDataSource
import com.example.tp_projeckt.data.token.TokenDataSourceImpl
import com.example.tp_projeckt.data.token.TokenDataStore
import com.example.tp_projeckt.data.token.TokenDataStoreImpl
import com.example.tp_projeckt.domain.login.authorization.LoginRepository
import com.example.tp_projeckt.domain.login.registration.RegistrationRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
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

    single<Interceptor> {
        HeaderInterceptor(tokenDataSource = get())
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

    fun provideLoginApiService(retrofit: Retrofit) = retrofit.create(LoginApi::class.java)

    single<LoginDataSource> {
        LoginDataSourceImpl(provideLoginApiService(get()))
    }

    single<LoginRepository> {
        LoginRepositoryImpl(
            loginDataSource = get(),
            tokenDataSource = get()
        )
    }

    single<TokenDataStore> {
        TokenDataStoreImpl(context = get())
    }

    single<TokenDataSource> {
        TokenDataSourceImpl(dataStore = get())
    }

    fun provideRegistrationApiService(retrofit: Retrofit) = retrofit.create(RegistrationApi::class.java)

    single<RegistrationDataSource> {
        RegistrationDataSourceImpl(provideRegistrationApiService(get()))
    }

    single {
        RegistrationCredentialsConverter()
    }

    single<RegistrationRepository> {
        RegistrationRepositoryImpl(
            registrationConverter = get(),
            registrationDataSource = get()
        )
    }
}