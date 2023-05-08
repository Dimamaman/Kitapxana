package uz.gita.dima.kitapxana.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookData(
    val author: String = "",
    val bookName: String = "",
    val bookUrl: String = "",
    val imageUrl: String = "",
    val genre: String = "",
    val page: String = "",
    val path: String = "",
    val startSize: String = ""
): Parcelable
