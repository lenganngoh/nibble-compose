package gohlengann.apps.nibble.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import gohlengann.apps.nibble.domain.model.Recipe
import gohlengann.apps.nibble.ui.detail.DetailActivity
import gohlengann.apps.nibble.ui.theme.NibbleTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NibbleTheme {
                HomeScreen(homeViewModel) {
                    startActivity(DetailActivity.getIntent(this, it))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        homeViewModel.searchRecipe("")
    }
}

fun DetailActivity.Companion.getIntent(
    activity: HomeActivity,
    it: Recipe
): Intent {
    return Intent(activity, DetailActivity::class.java).apply {
        putExtra(DetailActivity.EXTRA_RECIPE, it)
    }
}
