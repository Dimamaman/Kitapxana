package uz.gita.dima.kitapxana.domain.usescase.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData

interface BooksByCategoryUseCase {
    fun getBooksByCategory(): Flow<Result<List<BookData>>>
}