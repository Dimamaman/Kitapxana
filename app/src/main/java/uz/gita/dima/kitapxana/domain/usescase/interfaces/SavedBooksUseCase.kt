package uz.gita.dima.kitapxana.domain.usescase.interfaces

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData

interface SavedBooksUseCase {
    fun getSavedBooks(context: Context): Flow<Result<List<BookData>>>
}