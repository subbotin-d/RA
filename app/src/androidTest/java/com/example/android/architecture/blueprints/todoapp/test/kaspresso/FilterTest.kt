package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.TaskListScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToolbarScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.Test

// TODO Отфильтровать TODO по признаку «завершенные» и проверить что отобразились только завершенные
class FilterTest: BaseTest() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    @Test
    fun filterTest() = run {

        addTasks(3)

        TaskListScreen {
            markAsCompleted()
        }

        ToolbarScreen {
            filter {
                click()
            }
            completed {
                click()
            }
        }

        TaskListScreen {
            checkCompletedDisplayed()
        }
    }
}