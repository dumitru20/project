package com.example.tp_projeckt.di

import com.example.tp_projeckt.presentation.title.TitleVIewModel
import com.example.tp_projeckt.presentation.title.navigation.TitleRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

	viewModel<TitleVIewModel> {
		TitleVIewModel(router = get())
	}

	factory { TitleRouter(router = get()) }

	fun provideCicerone(): Cicerone<Router> =
		Cicerone.create()

	single { provideCicerone() }

	fun provideCiceroneRouter(cicerone: Cicerone<Router>): Router =
		cicerone.router

	single {
		provideCiceroneRouter(cicerone = get())
	}

	fun provideCiceroneNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder =
		cicerone.getNavigatorHolder()

	single {
		provideCiceroneNavigationHolder(cicerone = get())
	}
}