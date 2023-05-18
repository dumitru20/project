package com.example.tp_projeckt.di

import com.example.tp_projeckt.presentation.note.list.ListNoteViewModel
import com.example.tp_projeckt.presentation.note.list.navigation.ListNoteRouter
import com.example.tp_projeckt.presentation.title.TitleVIewModel
import com.example.tp_projeckt.presentation.title.navigation.TitleRouter
import com.example.tp_projeckt.presentation.login.authorization.AuthorizationViewModel
import com.example.tp_projeckt.presentation.login.authorization.navigation.AuthorizationRouter
import com.example.tp_projeckt.presentation.login.registration.RegistrationViewModel
import com.example.tp_projeckt.presentation.login.registration.navigation.RegistrationRouter
import com.example.tp_projeckt.presentation.note.create.CreateNoteViewModel
import com.example.tp_projeckt.presentation.note.create.navigation.CreateNoteRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

	viewModel<TitleVIewModel> {
		TitleVIewModel(router = get())
	}

	viewModel<AuthorizationViewModel> {
		AuthorizationViewModel(
			authenticateUseCase = get(),
			router = get()
		)
	}

	viewModel<RegistrationViewModel> {
		RegistrationViewModel(
			registrationUseCase = get(),
			router = get()
		)
	}

	viewModel<ListNoteViewModel> {
		ListNoteViewModel(
			getNotesUseCase = get(),
			router = get()
		)
	}

	viewModel<CreateNoteViewModel> {
		CreateNoteViewModel(
			createNoteUseCase = get(),
			router = get()
		)
	}

	factory { TitleRouter(router = get()) }

	factory { AuthorizationRouter(router = get()) }

	factory { RegistrationRouter(router = get()) }

	factory { ListNoteRouter(router = get()) }

	factory { CreateNoteRouter(router = get()) }

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