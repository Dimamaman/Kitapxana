package uz.gita.dima.kitapxana.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.kitapxana.domain.usescase.impl.AllBooksUseCaseImpl
import uz.gita.dima.kitapxana.domain.usescase.interfaces.AllBooksUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppUseCaseModule {

}