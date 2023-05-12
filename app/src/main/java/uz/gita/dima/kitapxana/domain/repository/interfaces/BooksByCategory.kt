package uz.gita.dima.kitapxana.domain.repository.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData

interface BooksByCategory {
    fun getBooksByCategory(): Flow<Result<List<BookData>>>
}