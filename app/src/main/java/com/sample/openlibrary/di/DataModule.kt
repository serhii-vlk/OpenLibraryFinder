package com.sample.openlibrary.di

import com.sample.openlibrary.data.remote.ErrorHandler
import com.sample.openlibrary.data.remote.RemoteErrorHandler
import com.sample.openlibrary.data.remote.api.ApiConstants
import com.sample.openlibrary.data.remote.api.OpenLibraryApi
import com.sample.openlibrary.data.remote.source.BooksRemoteDataSource
import com.sample.openlibrary.data.remote.source.BooksRemoteDataSourceImpl
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

interface DataProvider {
    fun provideBooksRemoteDataSource(): BooksRemoteDataSource
}

@Module(includes = [DataRemoteModule::class])
interface DataModule {
    @Binds
    fun RemoteErrorHandler.bindRemoteErrorHandler(): ErrorHandler

    @Binds
    fun BooksRemoteDataSourceImpl.bindBooksRemoteDataSource(): BooksRemoteDataSource
}

@Module
class DataRemoteModule {
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideOpenLibraryApi(retrofit: Retrofit): OpenLibraryApi = retrofit.create()
}
