package gohlengann.apps.nibble.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import gohlengann.apps.nibble.data.service.RecipeService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import gohlengann.apps.nibble.BuildConfig

private const val TIME_OUT = 30L

val NetworkModule = module {
    single {
        createRecipeService(BuildConfig.BASE_URL)
    }
}

fun createRecipeService(
    baseUrl: String
): RecipeService {
    val retrofit = createRetrofit(createOkHttpClient(), baseUrl)
    return retrofit.create(RecipeService::class.java)
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

fun createOkHttpClient(): OkHttpClient {

    val okHttpClientBuilder = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)

    return okHttpClientBuilder.build()
}