package com.example.me.presentation.di.module

import android.content.Context
import androidx.core.content.ContextCompat.getString
import com.example.me.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FirebaseModule {

	@Singleton
	@Provides
	fun provideBeginSignInRequest(@ApplicationContext context: Context): BeginSignInRequest =
		BeginSignInRequest.builder()
		.setGoogleIdTokenRequestOptions(
			BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
				.setSupported(true)
				.setServerClientId(getString(context, R.string.client_id))
				.setFilterByAuthorizedAccounts(true)
				.build())
		.build()

	@Singleton
	@Provides
	fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth
}