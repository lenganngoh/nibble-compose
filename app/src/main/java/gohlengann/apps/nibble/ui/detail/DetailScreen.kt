package gohlengann.apps.nibble.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import gohlengann.apps.nibble.R
import gohlengann.apps.nibble.domain.model.Recipe
import gohlengann.apps.nibble.ui.theme.Accent80
import gohlengann.apps.nibble.ui.theme.Secondary80

@Composable
fun DetailScreen(recipe: Recipe? = null) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->

        recipe?.let {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                item {
                    HeaderCard(recipe)
                }

                item {
                    FlowRow(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.detail_ingredients),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                        IngredientItem(recipe.ingredient1)
                        IngredientItem(recipe.ingredient2)
                        IngredientItem(recipe.ingredient3)
                        IngredientItem(recipe.ingredient4)
                        IngredientItem(recipe.ingredient5)
                        IngredientItem(recipe.ingredient6)
                        IngredientItem(recipe.ingredient7)
                        IngredientItem(recipe.ingredient8)
                        IngredientItem(recipe.ingredient9)
                        IngredientItem(recipe.ingredient10)
                        IngredientItem(recipe.ingredient11)
                        IngredientItem(recipe.ingredient12)
                        IngredientItem(recipe.ingredient13)
                        IngredientItem(recipe.ingredient14)
                        IngredientItem(recipe.ingredient15)
                        IngredientItem(recipe.ingredient16)
                        IngredientItem(recipe.ingredient17)
                        IngredientItem(recipe.ingredient18)
                        IngredientItem(recipe.ingredient19)
                        IngredientItem(recipe.ingredient20)
                    }
                }

                item {
                    InstructionBlock(recipe.instructions)
                }
            }
        }
    }
}

@Composable
fun InstructionBlock(instructions: String?) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.detail_instruction),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        FlowColumn {
            instructions?.split("\n")?.forEach {
                InstructionItem(it)
            }
        }
    }
}

@Composable
fun InstructionItem(instruction: String? = null) {
    if (instruction?.isNotEmpty() == true) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(3.dp),
            colors = CardDefaults.cardColors(
                containerColor = Secondary80
            )
        ) {
            Text(
                text = instruction,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun IngredientItem(ingredient: String? = null) {
    if (ingredient?.isNotEmpty() == true) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(3.dp),
            colors = CardDefaults.cardColors(
                containerColor = Secondary80
            )
        ) {
            Text(
                text = ingredient,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun HeaderCard(recipe: Recipe) {
    Box(modifier = Modifier.padding(16.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(3.dp),
            colors = CardDefaults.cardColors(
                containerColor = Secondary80
            )
        ) {
            Row {
                AsyncImage(
                    model = recipe.thumbnail,
                    contentDescription = "Thumbnail",
                    modifier = Modifier
                        .width(150.dp)
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                )

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = recipe.name ?: "",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = recipe.area ?: "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )

                    RecipeTagItem(recipe.tags)
                }
            }
        }
    }
}

@Composable
fun RecipeTagItem(tags: String?) {
    tags?.let {
        FlowRow(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val items = it.split(",").run {
                if (this.size > 5) this.take(5) else this
            }
            items.forEach { tag ->
                Box(
                    modifier = Modifier.background(
                        shape = RoundedCornerShape(9.dp),
                        color = Accent80
                    )
                ) {
                    Text(
                        text = tag,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 2.dp
                        )
                    )
                }
            }
        }
    }
}