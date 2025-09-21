package gohlengann.apps.nibble.domain.usecase

import gohlengann.apps.nibble.data.repository.RecipeRepository
import gohlengann.apps.nibble.domain.model.Recipe

class GetRecipesUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(query: String): List<Recipe> {
        // You can add extra business logic here if needed
        return repository.searchRecipes(query)
    }
}