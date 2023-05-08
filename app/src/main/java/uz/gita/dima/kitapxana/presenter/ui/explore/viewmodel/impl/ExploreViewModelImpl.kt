package uz.gita.dima.kitapxana.presenter.ui.explore.viewmodel.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dima.kitapxana.data.CategoryData
import uz.gita.dima.kitapxana.domain.AppRepository
import uz.gita.dima.kitapxana.presenter.ui.explore.viewmodel.ExploreViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModelImpl @Inject constructor(
    private val appRepository: AppRepository
): ViewModel(), ExploreViewModel {
    override val categoriesData = MutableLiveData<List<CategoryData>>()
    override val errorData = MutableLiveData<String>()



    override fun getBooksByCategory() {

            appRepository.getBooksByCategory()
                .onEach { result ->
                    result.onSuccess { list ->
                        val fantasyData = list.filter { it.genre == "fantasy" }
                        val algorithmData = list.filter { it.genre == "algorithm" }
                        val psychologyData = list.filter { it.genre == "psychology" }
                        val thrillerData = list.filter { it.genre == "thriller" }

                        Log.d("FFFF", "fantasyData -> ${fantasyData.size}")
                        Log.d("FFFF", "algorithmData -> ${algorithmData.size}")
                        Log.d("FFFF", "psychologyData -> ${psychologyData.size}")
                        Log.d("FFFF", "thrillerData -> ${thrillerData.size}")

                        categoriesData.value = listOf(
                            CategoryData("Fantasy", fantasyData),
                            CategoryData("algorithm", algorithmData),
                            CategoryData("psychology", psychologyData),
                            CategoryData("thriller", thrillerData),
                        )
                    }
                    result.onFailure { }
                }.launchIn(viewModelScope)
    }
}