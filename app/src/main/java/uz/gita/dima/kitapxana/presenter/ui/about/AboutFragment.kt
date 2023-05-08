package uz.gita.dima.kitapxana.presenter.ui.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dima.kitapxana.R
import uz.gita.dima.kitapxana.databinding.AboutFragmentBinding
import uz.gita.dima.kitapxana.presenter.ui.about.viewmodel.AboutViewModel
import uz.gita.dima.kitapxana.presenter.ui.about.viewmodel.impl.AboutViewModelImpl
import java.io.File

@AndroidEntryPoint
class AboutFragment : Fragment(R.layout.about_fragment) {

    private val args by navArgs<AboutFragmentArgs>()
    private val binding by viewBinding(AboutFragmentBinding::bind)
    private val viewModel: AboutViewModel by viewModels<AboutViewModelImpl>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookData = args.book
        val book = File(requireContext().filesDir, bookData.bookName)

        binding.apply {
            txtPage.text = "Page: ${bookData.page}"
            txtGenre.text = "Book Genre: ${bookData.genre}"
            txtName.text = "Book Name: ${bookData.bookName}"
            txtAuthor.text = "Book Author: ${bookData.author}"
            txtStar.text = "Book Stars: ${bookData.startSize}"
            Glide.with(requireContext()).load(bookData.imageUrl).into(imgBook)
            if (book.exists()) {
                btnDownload.setImageResource(R.drawable.ic_saved)
                btnDownload.isClickable = false
            } else {
                btnDownload.setImageResource(R.drawable.download)
                btnDownload.isClickable = true
            }

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }


            btnDownload.setOnClickListener {
                btnDownload.visibility = View.GONE
                binding.progress.visibility = View.VISIBLE
                viewModel.downloadBookByUrl(requireContext(), bookData)
            }
        }

        viewModel.error.observe(viewLifecycleOwner, errorObserver)
        viewModel.success.observe(viewLifecycleOwner, successObserver)
    }

    private val successObserver = Observer<String> {
        binding.progress.visibility = View.GONE
        binding.btnDownload.visibility = View.VISIBLE
        binding.btnDownload.setImageResource(R.drawable.ic_saved)
        binding.btnDownload.isClickable = false
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val errorObserver = Observer<String> {
        binding.progress.visibility = View.GONE
        binding.btnDownloadError.visibility = View.VISIBLE
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}