package id.application.sangugue.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.application.sangugue.data.remote.ApiService
import id.application.sangugue.data.repository.auth.AuthRepository
import id.application.sangugue.data.repository.auth.AuthRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://api.example.com/"

    @Provides
    @Singleton
    fun provideApiService(baseUrl: String): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideRepository(api: ApiService): AuthRepository {
        return AuthRepositoryImpl()
    }
}
