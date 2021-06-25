package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.AddTaskScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.TaskListScreen

open class BaseTest: MyTestCase() {

    protected fun addTasks(quantity: Int) {

        for (i in 1..quantity) {

            TaskListScreen {
                addButton {
                    click()
                }
            }

            AddTaskScreen {
                title {
                    replaceText("$TEST_TASK_TITLE $i")
                }
                description {
                    replaceText(TEST_TASK_DESCRIPTION)
                }
                saveButton {
                    click()
                }
            }
        }
    }
}