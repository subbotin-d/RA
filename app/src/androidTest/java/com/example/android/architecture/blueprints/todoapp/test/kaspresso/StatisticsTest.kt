package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.DrawerScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.StatisticsScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.TaskListScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToolbarScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.Test

// TODO Проверить что статистика отображается корректно, в соответствии
//  с завершенными/незавершенными
class StatisticsTest: BaseTest() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    @Test
    fun checkStatistics() = run {

        addTasks(1)

        //mark as completed 33.3% of tasks, means 1 task of 3
        TaskListScreen {
            markAsCompleted(1)
        }

        // go to statistics fragment
        DrawerScreen {
            drawer {
                open()
            }
            statistics {
                click()
            }
        }

        // check for statistics is correct
        StatisticsScreen {
            active {
                hasText(String.format("Active tasks: %.1f%%", 66.7f))
            }
            completed {
                hasText(String.format("Completed tasks: %.1f%%", 33.3f))
            }
        }
    }
}