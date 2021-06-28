package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.espresso.TodoListScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.AddToDoScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoListScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoListScreen.filteringTitle
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoListScreen.menuFilterItemCompleted
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoListScreen.menuFilterToDoButton
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoListScreen.todos
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class ToDoSortByCompletedAreDisplayed : TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    //Отфильтровать TODO по признаку «завершенные» и проверить что отобразились
    //только завершенные
    @Test
    fun checkRemoveToDo() =
        before {
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

        }.after {
            //Do nothing here
        }.run {
            TodoListScreen {
                todos.childAt<ToDoListScreen.TodoItem>(2) {
                    todoCheckBox {
                        click()
                    }
                }

                menuFilterToDoButton {
                    click()
                }

                menuFilterItemCompleted {
                    click()
                }

                filteringTitle {
                    isDisplayed()
                    hasText("Completed Tasks")
                }

                todos.childAt<ToDoListScreen.TodoItem>(0) {
                    todoCheckBox {
                        isChecked()
                    }

                    todoTitle {
                        isDisplayed()
                        hasText("New todo")
                    }
                }
            }

        }
}