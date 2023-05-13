package uz.gita.dima.kitapxana.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.kitapxana.domain.repository.impl.AllBooksRepositoryImpl
import uz.gita.dima.kitapxana.domain.repository.impl.BooksByCategoryImpl
import uz.gita.dima.kitapxana.domain.repository.impl.DownloadBookByUrlRepositoryImpl
import uz.gita.dima.kitapxana.domain.repository.impl.SavedBooksRepositoryImpl
import uz.gita.dima.kitapxana.domain.repository.interfaces.AllBooksRepository
import uz.gita.dima.kitapxana.domain.repository.interfaces.BooksByCategory
import uz.gita.dima.kitapxana.domain.repository.interfaces.DownloadBookByUrlRepository
import uz.gita.dima.kitapxana.domain.repository.interfaces.SavedBooksRepository
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
interface AppRepositoryModule {

    @[Binds Singleton]
    fun getAllBooksRepository(impl: AllBooksRepositoryImpl): AllBooksRepository

    @[Binds Singleton]
    fun getBooksByCategoryRepository(impl: BooksByCategoryImpl): BooksByCategory

    @[Binds Singleton]
    fun getDownloadBookByUrlRepository(impl: DownloadBookByUrlRepositoryImpl): DownloadBookByUrlRepository


    @[Binds Singleton]
    fun getSavedBooksRepository(impl: SavedBooksRepositoryImpl): SavedBooksRepository

}