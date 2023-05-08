package uz.gita.dima.kitapxana.presenter.ui.favorite.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import uz.gita.dima.kitapxana.data.BookData

interface FavoriteViewModel {
    val errorData: LiveData<String>
    val booksData: LiveData<List<BookData>>

    fun getAllData(context: Context)
}