package gohlengann.apps.nibble.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gohlengann.apps.nibble.domain.model.Recipe
import gohlengann.apps.nibble.domain.usecase.GetRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val error: String? = null
)

class HomeViewModel(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun searchRecipe(query: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            try {
                val recipes = getRecipesUseCase(query)
                _uiState.value = HomeUiState(recipes = recipes)
            } catch (e: Exception) {
                _uiState.value = HomeUiState(error = e.message)
            }
        }
    }
}