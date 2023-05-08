package uz.gita.dima.kitapxana.domain

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData

interface AppRepository {

    fun getAllData(): Flow<Result<List<BookData>>>
    fun getBooksByCategory(): Flow<Result<List<BookData>>>
    fun downloadBookByUrl(context: Context, bookData: BookData): Flow<Result<String>>
    fun getSavedBooks(context: Context): Flow<Result<List<BookData>>>
}