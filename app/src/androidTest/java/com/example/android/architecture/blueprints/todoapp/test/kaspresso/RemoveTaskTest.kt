package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToolbarScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.TaskListScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.Test

// TODO Удалить TODO и проверить что она удалилась
class RemoveTaskTest: BaseTest() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    @Test
    fun removeTask() = run {

        addTasks(1)

        // mark added task as completed
        TaskListScreen {
            taskListView {
                lastChild<TaskListScreen.TaskListItem> {
                    checkBox {
                        click()
                    }
                }
            }
        }

        // clear completed tasks
        ToolbarScreen {
            pressMenuKey()
            clear {
                click()
            }
        }

        // check added task removed
        TaskListScreen {
            taskListView {
                lastChild<TaskListScreen.TaskListItem> {
                    taskTitle.hasNoText("$TEST_TASK_TITLE 1")
                }
            }
        }
    }
}