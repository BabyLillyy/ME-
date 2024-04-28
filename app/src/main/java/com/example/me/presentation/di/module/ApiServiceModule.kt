package com.example.me.presentation.di.module

import com.example.me.BuildConfig
import com.example.me.data.api.ApiService
import com.example.me.data.api.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {

	@Singleton
	@Provides
	fun provideRetrofit(): Retrofit =
		Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl(BuildConfig.BASE_URL)
			.client(LoggingInterceptor.okHttpClient)
			.build()

	@Singleton
	@Provides
	fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)
}