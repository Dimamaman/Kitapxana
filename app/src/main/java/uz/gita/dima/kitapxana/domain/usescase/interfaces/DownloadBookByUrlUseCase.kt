package uz.gita.dima.kitapxana.domain.usescase.interfaces

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData

interface DownloadBookByUrlUseCase {
    fun downloadBookByUrl(context: Context, bookData: BookData): Flow<Result<String>>
}