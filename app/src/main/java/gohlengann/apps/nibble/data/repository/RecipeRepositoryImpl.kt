package gohlengann.apps.nibble.data.repository

import gohlengann.apps.nibble.data.service.RecipeService
import gohlengann.apps.nibble.domain.model.Recipe

class RecipeRepositoryImpl(
    private val api: RecipeService
) : RecipeRepository {

    override suspend fun searchRecipes(query: String): List<Recipe> {
        val response = api.searchRecipes(query)
        return response.meals
    }
}