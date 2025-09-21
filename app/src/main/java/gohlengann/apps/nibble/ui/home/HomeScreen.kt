package gohlengann.apps.nibble.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import gohlengann.apps.nibble.R
import gohlengann.apps.nibble.domain.model.Recipe
import gohlengann.apps.nibble.ui.theme.NibbleTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onRecipeClicked: (recipe: Recipe) -> Unit
) {

    val uiState = homeViewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = R.string.home_header),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        homeViewModel.searchRecipe(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        TextFieldHint()
                    }
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // 👈 2 columns
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(
                        count = uiState.value.recipes.size,
                        key = { uiState.value.recipes[it].id ?: it }) {
                        RecipeItem(
                            recipe = uiState.value.recipes[it],
                            onRecipeClicked = onRecipeClicked
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TextFieldHint() {
    Text(stringResource(id = R.string.home_search_hint))
}

@Composable
fun RecipeItem(recipe: Recipe, onRecipeClicked: (recipe: Recipe) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onRecipeClicked(recipe) }
    ) {
        AsyncImage(
            model = recipe.thumbnail,
            contentDescription = "Thumbnail",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(12.dp))
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = recipe.name ?: "",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = recipe.area ?: "",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NibbleTheme {
//        HomeScreen()
    }
}