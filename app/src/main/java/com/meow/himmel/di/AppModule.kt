package com.meow.himmel.di

import com.google.firebase.auth.FirebaseAuth
import com.meow.himmel.data.repository.AuthRepositoryImpl
import com.meow.himmel.domain.repository.AuthRepository
import com.meow.himmel.domain.use_case.UseCase
import com.meow.himmel.domain.use_case.auth.SignUp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideUseCase(
        authRepository: AuthRepository
    ): UseCase {
        return UseCase(
            signUp = SignUp(authRepository)
        )
    }
}