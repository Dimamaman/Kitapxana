package uz.gita.dima.kitapxana.presenter.ui.explore.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.dima.kitapxana.data.CategoryData

interface ExploreViewModel {

    val categoriesData: LiveData<List<CategoryData>>
    val errorData: LiveData<String>

    fun getBooksByCategory()
}