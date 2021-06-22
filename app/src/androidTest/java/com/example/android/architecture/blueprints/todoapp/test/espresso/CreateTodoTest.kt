package com.example.android.architecture.blueprints.todoapp.test.espresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.espresso.AddTodoScreen
import com.example.android.architecture.blueprints.todoapp.screen.espresso.TodoListScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class CreateTodoTest: TestCase() {

	@get:Rule
	val activityScenarioRule = ActivityScenarioRule<TasksActivity>(TasksActivity::class.java)

	@Test
	fun checkAddNewTodo() {
		TodoListScreen {
			tapOnAddTodoButton()
		}

		AddTodoScreen {
			fillTitle("My first note title")
			fillDescription("My first note description")
			tapOnSaveTodoButton()
		}

		//Waiting
		Thread.sleep(4000)
		TodoListScreen {
			checkTodoTitle("My first note title", 2)

			checkTotalTodoCount(3)
		}
	}
}