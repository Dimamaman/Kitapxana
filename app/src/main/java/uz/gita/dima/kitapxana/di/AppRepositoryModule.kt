package uz.gita.dima.kitapxana.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.kitapxana.domain.AppRepository
import uz.gita.dima.kitapxana.domain.impl.AppRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface AppRepositoryModule {

    @Binds
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository
}