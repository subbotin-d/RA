package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.agoda.kakao.text.KTextView
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.*
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import java.lang.Math.pow
import java.lang.Math.round

class TodoTests : TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<TasksActivity>(TasksActivity::class.java)

    @Test
    fun checkAddNewTodo() {

        TodoListScreen {
            addTodoFab.click()
        }

        AddTodoScreen {
            titleInput.replaceText("My first kaspresso note title")
            descriptionInput.replaceText("My first kaspresso note description")
            saveFab.click()
        }

        TodoListScreen {
            todoList {
                childAt<TodoListScreen.TodoListItem>(2) {
                    titleLabel.hasText("My first kaspresso note title")
                }
                hasSize(3)
            }
        }
    }

    @Test
    fun checkRemoveLastTodo() {
        TodoListScreen {
            todoList {
                childAt<TodoListScreen.TodoListItem>(0) {
                    click()
                }
            }
        }

        TodoDetailsScreen {
            deleteButton.click()
        }

        TodoListScreen {
            todoList {
                hasSize(1)
                children<TodoListScreen.TodoListItem> {
                    titleLabel.hasNoText("Build tower in Pisa")
                }
            }
        }
    }

    @Test
    fun checkCompletedTodoFilter() {
        TodoListScreen {
            todoList {
                firstChild<TodoListScreen.TodoListItem> {
                    doneCheckbox {
                        click()
                        isChecked()
                    }
                }
            }

            filterMenuButton.click()
            filterMenuCompletedButton.click()

            todoList {
                hasSize(1)
                children<TodoListScreen.TodoListItem> {
                    titleLabel.hasText("Build tower in Pisa")
                }
            }
        }
    }

    @Test
    fun checkCompletedTodoStatistics() {
        createTodoByFab("test title", "test description")

        TodoListScreen {
            todoList.firstChild<TodoListScreen.TodoListItem> {
                doneCheckbox {
                    click()
                    isChecked()
                }
            }
        }

        SideDrawerScreen {
            openStatisticsScreen()
        }

        StatisticsScreen {
            activeTasksLabel.containsText("66.7%")
            completedTodoLabel.containsText("33.3%")
        }
    }

    private fun createTodoByFab(title: String, description: String) {
        TodoListScreen {
            addTodoFab.click()
        }

        AddTodoScreen {
            titleInput.replaceText(title)
            descriptionInput.replaceText(description)
            saveFab.click()
        }
    }

    private fun Double.truncate(digits : Int) : Double {
        val mult = pow(10.0, digits.toDouble())
        return round(this * mult) / mult
    }

    fun KTextView.getText() : String {
        val textRequestAction = GetTextAction()
        view.perform(textRequestAction)
        return textRequestAction.textValue
    }

    class GetTextAction : ViewAction {

        var textValue : String = ""

        override fun getConstraints(): Matcher<View> =
                ViewMatchers.isAssignableFrom(TextView::class.java)

        override fun perform(uiController: UiController?, view: View?) {
            val tv = view as TextView
            textValue = tv.text.toString()
        }

        override fun getDescription(): String = "getting text from a TextView"
    }
}


