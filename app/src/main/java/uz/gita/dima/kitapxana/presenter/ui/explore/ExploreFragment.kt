package uz.gita.dima.kitapxana.presenter.ui.explore

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dima.kitapxana.R
import uz.gita.dima.kitapxana.data.CategoryData
import uz.gita.dima.kitapxana.databinding.ExploreFragmentBinding
import uz.gita.dima.kitapxana.presenter.homeadapter.ExploreAdapter
import uz.gita.dima.kitapxana.presenter.ui.explore.viewmodel.ExploreViewModel
import uz.gita.dima.kitapxana.presenter.ui.explore.viewmodel.impl.ExploreViewModelImpl
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment: Fragment(R.layout.explore_fragment) {
    @Inject
    lateinit var exploreAdapter: ExploreAdapter
    private val binding by viewBinding(ExploreFragmentBinding::bind)
    private val viewModel: ExploreViewModel by viewModels<ExploreViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getBooksByCategory()
        viewModel.errorData.observe(viewLifecycleOwner, errorData)
        viewModel.categoriesData.observe(viewLifecycleOwner, categoriesData)

        exploreAdapter.setClickListener {
            val action = ExploreFragmentDirections.actionExploreFragmentToAboutFragment(it)
            findNavController().navigate(action)
        }

        binding.apply {
            recycler.adapter = exploreAdapter
            recycler.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private val categoriesData = Observer<List<CategoryData>> {
        exploreAdapter.setData(it)
    }

    private val errorData = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}