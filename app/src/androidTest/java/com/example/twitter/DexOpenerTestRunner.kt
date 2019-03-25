package com.example.twitter

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener

@Suppress("unused")
class DexOpenerTestRunner : AndroidJUnitRunner() {
	@Throws(ClassNotFoundException::class, IllegalAccessException::class, InstantiationException::class)
	override fun newApplication(classLoader: ClassLoader, className: String, context: Context): Application {
		DexOpener.install(this)
		return super.newApplication(classLoader, className, context)
	}
}
