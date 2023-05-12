package uz.gita.dima.kitapxana.domain.repository.interfaces

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData

interface SavedBooksRepository {
    fun getSavedBooks(context: Context): Flow<Result<List<BookData>>>
}