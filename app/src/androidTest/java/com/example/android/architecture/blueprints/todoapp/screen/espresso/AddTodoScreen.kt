package com.example.android.architecture.blueprints.todoapp.screen.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.architecture.blueprints.todoapp.R

object AddTodoScreen : BaseScreen<AddTodoScreen>() {

	fun fillTitle(title: String) {
		onView(
			withId(R.id.add_task_title_edit_text)
		).perform(
			replaceText("My first note title"),
			closeSoftKeyboard()
		)
	}

	fun fillDescription(description: String) {
		onView(
			withId(R.id.add_task_description_edit_text)
		).perform(
			replaceText("My first note description"),
			closeSoftKeyboard()
		)
	}

	fun tapOnSaveTodoButton() {
		onView(withId(R.id.save_task_fab)).perform(click())
	}
}