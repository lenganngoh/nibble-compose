package gohlengann.apps.nibble.data.repository

import gohlengann.apps.nibble.domain.model.Recipe

interface RecipeRepository {
    suspend fun searchRecipes(query: String): List<Recipe>
}