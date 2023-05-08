package uz.gita.dima.kitapxana.presenter.ui.favorite.viewmodel.impl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.AppRepository
import uz.gita.dima.kitapxana.presenter.ui.favorite.viewmodel.FavoriteViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModelImpl @Inject constructor(
    private val appRepository: AppRepository
): ViewModel(), FavoriteViewModel {
    override val errorData = MutableLiveData<String>()
    override val booksData = MutableLiveData<List<BookData>>()

    override fun getAllData(context: Context) {
        appRepository.getSavedBooks(context).onEach {
            it.onSuccess {
                booksData.value = it
            }

            it.onFailure {
                errorData.value = it.message
            }
        }.launchIn(viewModelScope)
    }

}