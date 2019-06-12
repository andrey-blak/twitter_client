@file:JvmName("daggerMockRule")

package com.example.twitter

import androidx.test.platform.app.InstrumentationRegistry
import com.example.twitter.ui.TwitterApp
import com.example.twitter.ui.di.AppComponent
import com.example.twitter.ui.di.modules.ApiModule
import com.example.twitter.ui.di.modules.DatabaseModule
import com.example.twitter.ui.di.modules.PreferencesModule
import it.cosenonjaviste.daggermock.DaggerMock

fun daggerMockRule() = DaggerMock.rule<AppComponent>(
	ApiModule(),
	PreferencesModule(app),
	DatabaseModule(app)
) {
	set { component -> TwitterApp.component = component }
}

val app: TwitterApp get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TwitterApp
