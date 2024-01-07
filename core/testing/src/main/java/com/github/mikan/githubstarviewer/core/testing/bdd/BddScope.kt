package com.github.mikan.githubstarviewer.core.testing.bdd

sealed interface Scope
data object GivenScope : Scope
data object WhenScope : Scope
data object ThenScope : Scope

sealed interface SuspendScope : Scope
data object GivenSuspendScope : SuspendScope
data object WhenSuspendScope : SuspendScope
data object ThenSuspendScope : SuspendScope

