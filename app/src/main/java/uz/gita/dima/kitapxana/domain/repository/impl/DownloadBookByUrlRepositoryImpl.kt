package uz.gita.dima.kitapxana.domain.repository.impl

import android.content.Context
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.repository.interfaces.DownloadBookByUrlRepository
import java.io.File
import javax.inject.Inject


class DownloadBookByUrlRepositoryImpl @Inject constructor(): DownloadBookByUrlRepository {
    private val storage: FirebaseStorage = Firebase.storage
    override fun downloadBookByUrl(context: Context, bookData: BookData): Flow<Result<String>>  = callbackFlow {
        if (File(context.filesDir, bookData.bookName).exists()) {
            trySend(Result.success(bookData.bookName))
        } else {
            storage.reference.child(bookData.path)
                .getFile(File(context.filesDir, bookData.bookName))
                .addOnSuccessListener {
                    trySend(Result.success("Success"))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
                .addOnProgressListener {
//                    val progress = it.bytesTransferred * 100 / it.totalByteCount
                }
        }
        awaitClose()
    }
}