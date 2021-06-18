package com.example.android.architecture.blueprints.todoapp.screen.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.Matchers.allOf

object TodoListScreen : BaseScreen<TodoListScreen>() {

	fun tapOnAddTodoButton() {
		onView(withId(R.id.add_task_fab)).perform(click())
	}

	fun checkTodoTitle(title: String, atPosition: Int) {
		onView(
			allOf(
				withId(R.id.title_text),
				isDescendantOfA(
					withId(R.id.tasks_list)
				),
				withParent(
					withParentIndex(atPosition)
				)
			)
		).check(
			matches(
				allOf(
					withText(title),
					isDisplayed()
				)
			)
		)
	}

	fun checkTotalTodoCount(count: Int) {
		onView(withId(R.id.tasks_list)).check(matches(hasChildCount(count)))
	}
}



