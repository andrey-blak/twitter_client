@file:JvmName("Espresso")

package com.example.twitter.espressoktx

import android.app.Activity
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers

fun onView(viewId: Int): ViewInteraction {
	return Espresso.onView(ViewMatchers.withId(viewId))
}

fun ViewInteraction.click() {
	perform(ViewActions.click())
}

fun ViewInteraction.replaceText(text: String) {
	perform(ViewActions.replaceText(text), ViewActions.closeSoftKeyboard())
}

fun onToast(activity: Activity, messageResId: Int): ViewInteraction {
	return Espresso.onView(ViewMatchers.withText(messageResId))
		.inRoot(RootMatchers.withDecorView(Matchers.not(Matchers.`is`(activity.window.decorView))))
}
