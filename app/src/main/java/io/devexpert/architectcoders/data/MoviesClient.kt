package io.devexpert.architectcoders.data

import io.devexpert.architectcoders.BuildConfig
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.create

object MoviesClient {

    private val okHttpClient = okhttp3.OkHttpClient.Builder()
        .addInterceptor(::apiKeyAsQuery)
        .build()

    val instance = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .build()
        .create<MoviesService>()

}

private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
    chain.request()
        .newBuilder()
        .url(chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .build())
        .build()
)