package uz.gita.dima.kitapxana.presenter.ui.about.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import uz.gita.dima.kitapxana.data.BookData

interface AboutViewModel {
    val error: LiveData<String>
    val success: LiveData<String>

    fun downloadBookByUrl(context: Context,bookData: BookData)
}