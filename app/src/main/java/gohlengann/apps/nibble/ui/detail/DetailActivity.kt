package gohlengann.apps.nibble.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import gohlengann.apps.nibble.domain.model.Recipe
import gohlengann.apps.nibble.ui.home.HomeScreen
import gohlengann.apps.nibble.ui.home.HomeViewModel
import gohlengann.apps.nibble.ui.theme.NibbleTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val recipe = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_RECIPE, Recipe::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_RECIPE)
        }

        setContent {
            NibbleTheme {
                DetailScreen(recipe)
            }
        }
    }

    companion object {
        const val EXTRA_RECIPE = "EXTRA_RECIPE"
    }
}