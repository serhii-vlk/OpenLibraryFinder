package com.sample.openlibrary.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

@ExperimentalCoroutinesApi
class TestCoroutinesRule(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher(),
    val testScope: TestCoroutineScope = TestCoroutineScope(testDispatcher)
) : BeforeEachCallback, AfterEachCallback {
    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    fun runBlocking(block: suspend TestCoroutineScope.() -> Unit) =
        testDispatcher.runBlockingTest { block() }
}
