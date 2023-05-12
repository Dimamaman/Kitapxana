package uz.gita.dima.kitapxana.domain.repository.impl

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.repository.interfaces.BooksByCategory
import javax.inject.Inject

class BooksByCategoryImpl @Inject constructor() : BooksByCategory {
    private val db: FirebaseFirestore = Firebase.firestore
    override fun getBooksByCategory(): Flow<Result<List<BookData>>> = callbackFlow {
        db.collection("books")
            .get()
            .addOnCompleteListener {
                val list = arrayListOf<BookData>()
                if (it.isSuccessful) {
                    for (document in it.result) {
                        list.add(document.toObject(BookData::class.java))
                    }
                    Log.d("RRR","getBookByCategory size -> ${list.size}")
                    trySend(Result.success(list))
                }
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }
}