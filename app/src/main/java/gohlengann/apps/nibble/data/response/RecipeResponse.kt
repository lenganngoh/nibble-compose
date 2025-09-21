package gohlengann.apps.nibble.data.response

import com.google.gson.annotations.SerializedName
import gohlengann.apps.nibble.domain.model.Recipe

data class RecipeResponse (
    @SerializedName("meals")
    val meals: List<Recipe>
)