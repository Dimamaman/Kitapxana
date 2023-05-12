package uz.gita.dima.kitapxana.domain.usescase.impl

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.repository.interfaces.SavedBooksRepository
import uz.gita.dima.kitapxana.domain.usescase.interfaces.SavedBooksUseCase
import javax.inject.Inject

class SavedBooksUseCaseImpl @Inject constructor(
    private val savedBooksRepository: SavedBooksRepository
): SavedBooksUseCase {
    override fun getSavedBooks(context: Context): Flow<Result<List<BookData>>>  = savedBooksRepository.getSavedBooks(context)
}