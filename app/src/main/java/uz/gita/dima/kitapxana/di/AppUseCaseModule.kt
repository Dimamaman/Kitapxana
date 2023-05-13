package uz.gita.dima.kitapxana.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.kitapxana.domain.usescase.impl.AllBooksUseCaseImpl
import uz.gita.dima.kitapxana.domain.usescase.impl.BooksByCategoryUseCaseImpl
import uz.gita.dima.kitapxana.domain.usescase.impl.DownloadBookByUrlUseCaseImpl
import uz.gita.dima.kitapxana.domain.usescase.impl.SavedBooksUseCaseImpl
import uz.gita.dima.kitapxana.domain.usescase.interfaces.AllBooksUseCase
import uz.gita.dima.kitapxana.domain.usescase.interfaces.BooksByCategoryUseCase
import uz.gita.dima.kitapxana.domain.usescase.interfaces.DownloadBookByUrlUseCase
import uz.gita.dima.kitapxana.domain.usescase.interfaces.SavedBooksUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppUseCaseModule {
    @[Binds Singleton]
    fun getAllBooksUsesCase(impl: AllBooksUseCaseImpl): AllBooksUseCase

    @[Binds Singleton]
    fun getBooksCategoryUseCase(impl: BooksByCategoryUseCaseImpl): BooksByCategoryUseCase

    @[Binds Singleton]
    fun getDownloadBookByUrlUseCase(impl: DownloadBookByUrlUseCaseImpl): DownloadBookByUrlUseCase

    @[Binds Singleton]
    fun getSavedBooksUseCase(impl: SavedBooksUseCaseImpl): SavedBooksUseCase
}