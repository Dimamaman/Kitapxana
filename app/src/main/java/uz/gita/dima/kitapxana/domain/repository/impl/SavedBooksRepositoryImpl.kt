package uz.gita.dima.kitapxana.domain.repository.impl

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.repository.interfaces.SavedBooksRepository
import java.io.File
import javax.inject.Inject

class SavedBooksRepositoryImpl @Inject constructor(): SavedBooksRepository {
    private val db: FirebaseFirestore = Firebase.firestore
    override fun getSavedBooks(context: Context): Flow<Result<List<BookData>>>  = callbackFlow {
        db.collection("books").get()
            .addOnSuccessListener {
                val list = arrayListOf<BookData>()
                it.forEach { data ->
                    val book = File(context.filesDir, data.get("bookName").toString())
                    Log.d("RRR","File book -> $book")
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