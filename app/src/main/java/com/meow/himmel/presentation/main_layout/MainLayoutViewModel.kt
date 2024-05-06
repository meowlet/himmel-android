package com.meow.himmel.presentation.main_layout

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meow.himmel.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainLayoutViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _state = mutableStateOf(MainLayoutState())
    val state: State<MainLayoutState> = _state

    private val _eventFlow = MutableSharedFlow<MainLayoutUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: MainLayoutEvent) {
        when (event) {
            is MainLayoutEvent.NavigationChange -> viewModelScope.launch {
                _eventFlow.emit(MainLayoutUiEvent.Navigate(event.route))
                _state.value = state.value.copy(currentRoute = event.route)
            }
        }
    }
}