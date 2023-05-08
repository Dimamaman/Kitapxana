package uz.gita.dima.kitapxana.domain.impl

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.AppRepository
import java.io.File
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(): AppRepository {
    private val db = Firebase.firestore
    private val storage = Firebase.storage

    override fun getAllData(): Flow<Result<List<BookData>>> = callbackFlow {
        db.collection("books")
            .get()
            .addOnSuccessListener { querySnapshot ->
                Log.d("MMM", "docs size -> ${querySnapshot.documents.size}")

                val datum = arrayListOf<BookData>()
                querySnapshot.forEach {
                    val imageData = BookData(
                        it.get("author") as String,
                        it.get("bookName") as String,
                        it.get("bookUrl") as String,
                        it.get("imageUrl") as String,
                        it.get("genre") as String,
                        it.get("path") as String,
                        it.get("startSize") as String
                    )
                    datum.add(imageData)
                }
                trySend(Result.success(datum))

            }.addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getBooksByCategory(): Flow<Result<List<BookData>>> = callbackFlow {
        db.collection("books")
            .get()
            .addOnCompleteListener {
                val list = arrayListOf<BookData>()
                if (it.isSuccessful) {
                    for (document in it.result) {
                        list.add(document.toObject(BookData::class.java))
                    }
                    Log.d("RRR","List size -> ${list.size}")
                    trySend(Result.success(list))
                }
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun downloadBookByUrl(context: Context, bookData: BookData): Flow<Result<String>> = callbackFlow {
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

    override fun getSavedBooks(context: Context): Flow<Result<List<BookData>>> = callbackFlow {
        db.collection("books").get()
            .addOnSuccessListener {
                val list = arrayListOf<BookData>()
                it.forEach { data ->
                    val book = File(context.filesDir, data.get("bookName").toString())
                    Log.d("UUU","File book -> $book")
                    if (book.exists()) {
                        val temp = data.toObject(BookData::class.java)
                        list.add(temp)
                    }
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }
}










