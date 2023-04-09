package com.example.tp_projeckt.app

import android.app.Application
import com.example.tp_projeckt.di.dataModule
import com.example.tp_projeckt.di.domainModule
import com.example.tp_projeckt.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidLogger(Level.DEBUG)
			androidContext(this@App)
			modules(listOf(dataModule, domainModule, presentationModule))
		}
	}
}