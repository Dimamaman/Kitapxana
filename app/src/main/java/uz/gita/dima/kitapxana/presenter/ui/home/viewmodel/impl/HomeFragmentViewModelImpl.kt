package uz.gita.dima.kitapxana.presenter.ui.home.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.usescase.interfaces.AllBooksUseCase
import uz.gita.dima.kitapxana.presenter.ui.home.viewmodel.HomeFragmentViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModelImpl @Inject constructor(
    private val allBooksUseCase: AllBooksUseCase
): ViewModel(), HomeFragmentViewModel {
    override val errorMessage = MutableLiveData<String>()
    override val allImages = MutableLiveData<List<BookData>>()

    override fun getAll() {
        allBooksUseCase.getAllData().onEach {
            it.onSuccess {
                allImages.value = it
            }
            it.onFailure {
                errorMessage.value = it.message
            }
        }.launchIn(viewModelScope)
    }

}