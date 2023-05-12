package uz.gita.dima.kitapxana.domain.usescase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.repository.interfaces.AllBooksRepository
import uz.gita.dima.kitapxana.domain.usescase.interfaces.AllBooksUseCase
import javax.inject.Inject


class AllBooksUseCaseImpl @Inject constructor(
    private val allBooksRepository: AllBooksRepository
) : AllBooksUseCase {
    override fun getAllData(): Flow<Result<List<BookData>>> = allBooksRepository.getAllData()
}