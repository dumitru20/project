package com.example.tp_projeckt.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tp_projeckt.R
import com.example.tp_projeckt.presentation.title.navigation.TitleDestination
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

	private val router: Router by inject()

	private val navigatorHolder: NavigatorHolder by inject()

	private val navigator = AppNavigator(this, R.id.fragmentTitle)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if (savedInstanceState == null) {
			router.newRootScreen(TitleDestination)
		}
	}

	override fun onResumeFragments() {
		super.onResumeFragments()
		navigatorHolder.setNavigator(navigator)
	}

	override fun onPause() {
		navigatorHolder.removeNavigator()
		super.onPause()
	}
}