package gohlengann.apps.nibble.di

import gohlengann.apps.nibble.data.repository.RecipeRepository
import gohlengann.apps.nibble.data.repository.RecipeRepositoryImpl
import gohlengann.apps.nibble.domain.usecase.GetRecipesUseCase
import gohlengann.apps.nibble.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FeatureModule = module {
    viewModel { HomeViewModel(get()) }

    single { GetRecipesUseCase(get()) }
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
}