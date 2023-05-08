package uz.gita.dima.kitapxana.presenter.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dima.kitapxana.R
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.data.local.sharedPref.SharedPref
import uz.gita.dima.kitapxana.databinding.HomeFragmentBinding
import uz.gita.dima.kitapxana.presenter.homeadapter.HomeAdapter
import uz.gita.dima.kitapxana.presenter.ui.home.viewmodel.HomeFragmentViewModel
import uz.gita.dima.kitapxana.presenter.ui.home.viewmodel.impl.HomeFragmentViewModelImpl
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {
    private val binding by viewBinding(HomeFragmentBinding::bind)
    private val viewModel: HomeFragmentViewModel by viewModels<HomeFragmentViewModelImpl>()

    @Inject
    lateinit var sharedPref: SharedPref
    @Inject
    lateinit var homeAdapter: HomeAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAll()
        binding.rvHorizontal.adapter = homeAdapter
        viewModel.allImages.observe(viewLifecycleOwner, allImagesObserver)

        if (sharedPref.bookName!!.isEmpty()) {
            binding.linear2.visibility = View.GONE
        } else {
            binding.linear2.visibility = View.VISIBLE
            binding.page.text = "Page: ${sharedPref.savedPage.toString()}"
            binding.bookName.text = "Book Name: ${sharedPref.bookName}"
        }

        homeAdapter.setClickBook {
            val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(it)
            findNavController().navigate(action)
        }

        binding.btnLastBook.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToReadBookFragment(
                sharedPref.savedPage,sharedPref.totalPage, sharedPref.bookName!!
            )
            findNavController().navigate(action)
        }
    }

    private val allImagesObserver = Observer<List<BookData>> {
        homeAdapter.submitList(it)
    }
}