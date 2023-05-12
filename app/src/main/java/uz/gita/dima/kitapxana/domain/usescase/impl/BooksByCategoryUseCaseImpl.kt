package uz.gita.dima.kitapxana.domain.usescase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.repository.interfaces.BooksByCategory
import uz.gita.dima.kitapxana.domain.usescase.interfaces.BooksByCategoryUseCase
import javax.inject.Inject

class BooksByCategoryUseCaseImpl @Inject constructor(
    private val booksByCategory: BooksByCategory
): BooksByCategoryUseCase {
    override fun getBooksByCategory(): Flow<Result<List<BookData>>> = booksByCategory.getBooksByCategory()
}