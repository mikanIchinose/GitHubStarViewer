package com.github.mikan.githubstarviewer.core.testing.bdd

import kotlinx.coroutines.test.TestScope


fun Given(block: GivenScope.() -> Unit) = GivenScope.block()
fun When(block: WhenScope.() -> Unit) = WhenScope.block()
fun WhenScope.Then(block: ThenScope.() -> Unit) = ThenScope.block()

suspend fun TestScope.GivenSuspend(
    block: suspend GivenSuspendScope.() -> Unit,
) = GivenSuspendScope.block()

suspend fun TestScope.WhenSuspend(
    block: suspend WhenSuspendScope.() -> Unit,
) = WhenSuspendScope.block()

suspend fun WhenSuspendScope.ThenSuspend(
    block: suspend ThenSuspendScope.() -> Unit,
) = ThenSuspendScope.block()
