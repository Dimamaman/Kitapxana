package uz.gita.dima.kitapxana.domain.repository.impl

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.repository.interfaces.AllBooksRepository
import javax.inject.Inject

class AllBooksRepositoryImpl @Inject constructor(): AllBooksRepository {
    private val db: FirebaseFirestore = Firebase.firestore
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
                        it.get("page") as String,
                        it.get("path") as String,
                        it.get("startSize") as String
                    )
                    datum.add(imageData)
                    Log.d("RRR","getAllData size -> $imageData")
                }
                trySend(Result.success(datum))

            }.addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }
}