package com.meow.himmel.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.meow.himmel.data.repository.AuthRepositoryImpl
import com.meow.himmel.data.repository.UserRepositoryImpl
import com.meow.himmel.domain.repository.AuthRepository
import com.meow.himmel.domain.repository.UserRepository
import com.meow.himmel.domain.use_case.UseCase
import com.meow.himmel.domain.use_case.auth.SignIn
import com.meow.himmel.domain.use_case.auth.SignOut
import com.meow.himmel.domain.use_case.auth.SignUp
import com.meow.himmel.domain.use_case.auth.UserCheck
import com.meow.himmel.domain.use_case.user.DeleteUser
import com.meow.himmel.domain.use_case.user.GetUserInfo
import com.meow.himmel.domain.use_case.user.UpdateAvatar
import com.meow.himmel.domain.use_case.user.UpdateUser
import com.meow.himmel.presentation.util.Constants
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
    fun provideUserDatabase(): DatabaseReference {
        return Firebase.database.getReference(Constants.USER_DATABASE_REF)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userDatabase: DatabaseReference
    ): UserRepository {
        return UserRepositoryImpl(userDatabase)
    }

    @Provides
    @Singleton
    fun provideUseCase(
        authRepository: AuthRepository,
        userRepository: UserRepository
    ): UseCase {
        return UseCase(
            userCheck = UserCheck(authRepository),
            signUp = SignUp(authRepository),
            signIn = SignIn(authRepository),
            signOut = SignOut(authRepository),
            getUserInfo = GetUserInfo(userRepository),
            deleteUser = DeleteUser(userRepository),
            updateUser = UpdateUser(userRepository),
            updateAvatar = UpdateAvatar(userRepository)
        )
    }
}