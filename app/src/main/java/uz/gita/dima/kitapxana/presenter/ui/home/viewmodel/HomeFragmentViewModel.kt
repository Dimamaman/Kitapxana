package uz.gita.dima.kitapxana.presenter.ui.home.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.dima.kitapxana.data.BookData

interface HomeFragmentViewModel {
    val errorMessage: LiveData<String>
    val allImages: LiveData<List<BookData>>

    fun getAll()
}