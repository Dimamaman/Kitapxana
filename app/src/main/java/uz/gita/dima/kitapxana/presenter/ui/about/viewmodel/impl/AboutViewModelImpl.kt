package uz.gita.dima.kitapxana.presenter.ui.about.viewmodel.impl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.domain.usescase.interfaces.DownloadBookByUrlUseCase
import uz.gita.dima.kitapxana.presenter.ui.about.viewmodel.AboutViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModelImpl @Inject constructor(
    private val downloadBookByUrlUseCase: DownloadBookByUrlUseCase
): ViewModel(),AboutViewModel {
    override val error = MutableLiveData<String>()
    override val success = MutableLiveData<String>()

    override fun downloadBookByUrl(context: Context, bookData: BookData) {
        downloadBookByUrlUseCase.downloadBookByUrl(context,bookData).onEach {
            it.onSuccess {
                success.value = it
            }

            it.onFailure {
                error.value = it.message
            }
        }.launchIn(viewModelScope)
    }
}