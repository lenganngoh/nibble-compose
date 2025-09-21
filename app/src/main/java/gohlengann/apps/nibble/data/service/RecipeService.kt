package gohlengann.apps.nibble.data.service

import gohlengann.apps.nibble.data.response.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {
    @GET("/api/json/v1/1/search.php")
    suspend fun searchRecipes(@Query("s") query: String? = null): RecipeResponse

}