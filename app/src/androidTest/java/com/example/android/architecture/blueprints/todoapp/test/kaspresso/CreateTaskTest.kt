package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.TaskListScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.Test

// TODO Добавить TODO и проверить что она добавилась
class CreateTaskTest: BaseTest() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    @Test
    fun addNewTask() = run {

        addTasks(1)

        // check added task is displayed
        TaskListScreen {
            taskListView {
                childWith<TaskListScreen.TaskListItem> {
                    withDescendant {
                        withText("$TEST_TASK_TITLE 1")
                    }
                }.isDisplayed()
            }
        }
    }
}