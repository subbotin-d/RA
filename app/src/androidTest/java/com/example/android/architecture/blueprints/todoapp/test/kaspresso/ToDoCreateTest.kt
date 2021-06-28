package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.espresso.TodoListScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.AddToDoScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoListScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoListScreen.menuFilterToDoButton
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoListScreen.todos
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class ToDoCreateTest : TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    //Добавить TODO и проверить что она добавилась
    @Test
    fun checkAddToDo() {
        run {
            ToDoListScreen {
                addToDoButton {
                    click()
                }
            }

            AddToDoScreen {
                title {
                    replaceText("New todo")
                }

                description {
                    replaceText("New description")
                }

                saveButton {
                    click()
                }
            }

            TodoListScreen {
                todos.childAt<ToDoListScreen.TodoItem>(2) {

                    todoCheckBox {
                        isNotChecked()
                    }

                    todoTitle {
                        isDisplayed()
                        hasText("New todo")
                    }
                }
            }
        }
    }
}
