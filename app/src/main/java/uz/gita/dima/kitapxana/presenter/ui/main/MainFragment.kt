package uz.gita.dima.kitapxana.presenter.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dima.kitapxana.R
import uz.gita.dima.kitapxana.databinding.MainFragmentBinding
import uz.gita.dima.kitapxana.presenter.ui.explore.ExploreFragment
import uz.gita.dima.kitapxana.presenter.ui.favorite.FavoriteFragment
import uz.gita.dima.kitapxana.presenter.ui.home.HomeFragment
import uz.gita.dima.kitapxana.presenter.ui.user.UserFragment

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {
    private val binding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController =
            (childFragmentManager.findFragmentById(R.id.main_fragmentContainerView) as NavHostFragment)
                .navController

        binding.bottomNavigationView.setupWithNavController(navController)

        /*binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Navigation.findNavController(
                        requireActivity(),
                        R.id.main_fragment_container_view
                    ).navigate(R.id.homeFragment)
                }

                R.id.favoriteFragment -> {
                    Navigation.findNavController(
                        requireActivity(),
                        R.id.main_fragment_container_view
                    ).navigate(R.id.favoriteFragment)
                }

                R.id.exploreFragment -> {
                    Navigation.findNavController(
                        requireActivity(),
                        R.id.main_fragment_container_view
                    ).navigate(R.id.exploreFragment)
                }

                R.id.userFragment -> {
                    Navigation.findNavController(
                        requireActivity(),
                        R.id.main_fragment_container_view
                    ).navigate(R.id.userFragment)
                }
            }
            true
        }*/
    }

    fun goneBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.GONE
    }
}