package com.example.composeApp.screens.CommonsUI

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import kotlin.math.roundToInt


class PagerState(
    currentPage: Int = 0,
    minPage: Int = 0,
    maxPage: Int = 0
) {
    private var _minPage by mutableStateOf(minPage)
    var minPage: Int
        get() = _minPage
        set(value) {
            _minPage = value.coerceAtMost(_maxPage)
            _currentPage = _currentPage.coerceIn(_minPage, _maxPage)
        }

    private var _maxPage by mutableStateOf(maxPage, structuralEqualityPolicy())
    var maxPage: Int
        get() = _maxPage
        set(value) {
            _maxPage = value.coerceAtLeast(_minPage)
            _currentPage = _currentPage.coerceIn(_minPage, maxPage)
        }

    private var _currentPage by mutableStateOf(currentPage.coerceIn(minPage, maxPage))
    var currentPage: Int
        get() = _currentPage
        set(value) {
            _currentPage = value.coerceIn(minPage, maxPage)
        }

    var selectionState by mutableStateOf(SelectionState.Selected)

    suspend inline fun <R> selectPage(block: PagerState.() -> R): R = try {
        selectionState = SelectionState.Undecided
        block()
    } finally {
        selectPage()
    }

    suspend fun selectPage() {
        currentPage -= currentPageOffset.roundToInt()
        snapToOffset(0f)
        selectionState = SelectionState.Selected
    }

    private var _currentPageOffset = Animatable(0f).apply {
        updateBounds(-1f, 1f)
    }
    val currentPageOffset: Float
        get() = _currentPageOffset.value

    suspend fun snapToOffset(offset: Float) {
        val max = if (currentPage == minPage) 0f else 1f
        val min = if (currentPage == maxPage) 0f else -1f
        _currentPageOffset.snapTo(offset.coerceIn(min, max))
    }

    suspend fun fling(velocity: Float) {
        if (velocity < 0 && currentPage == maxPage) {
            currentPage = minPage
        } else if (velocity > 0 && currentPage == minPage) return

        _currentPageOffset.animateTo(currentPageOffset.roundToInt().toFloat())
        selectPage()
    }

    override fun toString(): String =
        "PagerState{minPage=$minPage, maxPage=$maxPage, " +
                "currentPage=$currentPage, currentPageOffset=$currentPageOffset}"
}
enum class SelectionState {
    Selected,
    Undecided
}


class PagerScope(
    private val state: PagerState,
    val commingPage: Int
) {
    /**
     * Returns the current selected page
     */
    val currentPage: Int
        get() = state.currentPage

    /**
     * Returns the current selected page offset
     */
    val currentPageOffset: Float
        get() = state.currentPageOffset

    /**
     * Returns the current selection state
     */
    val selectionState: SelectionState
        get() = state.selectionState
}