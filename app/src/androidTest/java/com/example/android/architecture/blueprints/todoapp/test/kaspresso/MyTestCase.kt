package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

open class MyTestCase: TestCase(getBuilder()) {

    companion object {

        const val TEST_TASK_TITLE = "Test task title"
        const val TEST_TASK_DESCRIPTION = "Test task description"

        private val myBuilder: Kaspresso.Builder
            get() = Kaspresso.Builder.simple().apply {
                flakySafetyParams = FlakySafetyParams.default().apply {
                    timeoutMs = 3_000
                }
            }
        fun getBuilder() = myBuilder
    }
}