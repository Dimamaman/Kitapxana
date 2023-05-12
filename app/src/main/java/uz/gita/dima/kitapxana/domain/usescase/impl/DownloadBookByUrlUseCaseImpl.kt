package uz.gita.dima.kitapxana.domain.usescase.impl

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.repository.interfaces.DownloadBookByUrlRepository
import uz.gita.dima.kitapxana.domain.usescase.interfaces.DownloadBookByUrlUseCase
import javax.inject.Inject

class DownloadBookByUrlUseCaseImpl @Inject constructor(
    private val downloadBookByUrlRepository: DownloadBookByUrlRepository
): DownloadBookByUrlUseCase {
    override fun downloadBookByUrl(context: Context, bookData: BookData): Flow<Result<String>> {
        return downloadBookByUrlRepository.downloadBookByUrl(context,bookData)
    }
}