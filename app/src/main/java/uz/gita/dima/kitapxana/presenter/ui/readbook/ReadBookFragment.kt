package uz.gita.dima.kitapxana.presenter.ui.readbook

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.github.barteksc.pdfviewer.util.FitPolicy
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dima.kitapxana.R
import uz.gita.dima.kitapxana.data.local.sharedPref.SharedPref
import uz.gita.dima.kitapxana.databinding.ReadBookFragmentBinding
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class ReadBookFragment: Fragment(R.layout.read_book_fragment) , OnPageChangeListener,
    OnPageErrorListener {

    private var bookName = ""
    private var pageNumber = 0
    private var totalPage = 0
    @Inject lateinit var sharedPref: SharedPref
    private val arg by navArgs<ReadBookFragmentArgs>()
    private val binding by viewBinding(ReadBookFragmentBinding::bind)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookName = arg.bookName
        val savedPage = arg.savedPage
        totalPage = arg.totalPage
        pageNumber = savedPage
        val b = requireActivity().filesDir
        b?.let {
            val file = File(it, bookName)
            if (file.exists()) {
                binding.txtPages.text = "$savedPage/$totalPage"
                binding.pdfView.fromFile(file)
                    .enableSwipe(true)
                    .defaultPage(pageNumber)
                    .swipeHorizontal(false)
                    .pageSnap(true)
                    .autoSpacing(true)
                    .pageFling(true)
                    .enableDoubletap(true)
                    .enableAnnotationRendering(false)
                    .scrollHandle(DefaultScrollHandle(requireContext()))
                    .onPageChange(this)
                    .onPageError(this)
                    .enableAntialiasing(true)
                    .spacing(10)
                    .nightMode(false)
                    .pageFitPolicy(FitPolicy.BOTH)
                    .load()
            } else {
                Toast.makeText(requireContext(), "Book is not downloaded", Toast.LENGTH_SHORT).show()
            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (pageNumber != 0) {
            sharedPref.bookName = bookName
            sharedPref.savedPage = pageNumber
            sharedPref.totalPage = totalPage
        } else if (pageNumber == totalPage) {
            sharedPref.bookName = ""
            sharedPref.savedPage = 0
            sharedPref.totalPage = 0
        }
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
        binding.txtPages.text = String.format("%s / %s", page + 1, pageCount)
    }

    override fun onPageError(page: Int, t: Throwable?) {

    }
}