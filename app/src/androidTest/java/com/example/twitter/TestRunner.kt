package com.example.twitter

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins

@Suppress("unused")
class TestRunner : AndroidJUnitRunner() {
	@Throws(ClassNotFoundException::class, IllegalAccessException::class, InstantiationException::class)
	override fun newApplication(classLoader: ClassLoader, className: String, context: Context): Application {
		DexOpener.install(this)
		return super.newApplication(classLoader, className, context)
	}

	override fun onStart() {
		RxJavaPlugins.setInitComputationSchedulerHandler(Rx2Idler.create("RxJava Computation Scheduler"))
		RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("RxJava IO Scheduler"))
		RxJavaPlugins.setInitNewThreadSchedulerHandler(Rx2Idler.create("RxJava New Thread Scheduler"))
		RxJavaPlugins.setInitSingleSchedulerHandler(Rx2Idler.create("RxJava Single Scheduler"))
		super.onStart()
	}
}
