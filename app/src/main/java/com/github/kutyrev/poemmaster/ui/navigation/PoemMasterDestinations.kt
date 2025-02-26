package com.github.kutyrev.poemmaster.ui.navigation

import kotlinx.serialization.Serializable

sealed class PoemMasterDestinations {
    @Serializable
    data object Home

    @Serializable
    data class PoemDestinations(val id: Int)
}