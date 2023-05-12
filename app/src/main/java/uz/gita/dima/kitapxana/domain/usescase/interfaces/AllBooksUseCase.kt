package uz.gita.dima.kitapxana.domain.usescase.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData

interface AllBooksUseCase {
    fun getAllData(): Flow<Result<List<BookData>>>
}