package uz.gita.dima.kitapxana.presenter.ui.readbook

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dima.kitapxana.R
import uz.gita.dima.kitapxana.databinding.ReadBookFragmentBinding
import java.io.File

@AndroidEntryPoint
class ReadBookFragment: Fragment(R.layout.read_book_fragment) {
    private var pageNumber = 0
    private val arg by navArgs<ReadBookFragmentArgs>()
    private val binding by viewBinding(ReadBookFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = arg.book
//        val path = requireContext().filesDir
//        val b = path.name

        Log.d("VVV","File name -> $book")

//        val file = File(requireActivity().filesDir,book.bookName)
        val b = requireActivity().filesDir
        b?.let {
            val file = File(it, book.bookName)
            if (file.exists()) {
                Log.d("VVV","File name -> true")
//                binding.pdfView.fromFile(file)
//                    .enableSwipe(true)
//                    .defaultPage(0)
//                    .swipeHorizontal(false)
//                    .enableDoubletap(true)
//                    .scrollHandle(null)
//                    .enableAntialiasing(true)
//                    .spacing(0)
//                    .load()
            } else {
                Toast.makeText(requireContext(), "Book is not downloaded", Toast.LENGTH_SHORT).show()
            }
        }

        Log.d("VVV","File name -> $b")



        val a = "Dima"
//        if (file.exists()) {
//            binding.pdfView.fromFile(file)
//                .enableSwipe(true)
//                .defaultPage(0)
//                .swipeHorizontal(false)
//                .enableDoubletap(true)
//                .scrollHandle(null)
//                .enableAntialiasing(true)
//                .spacing(0)
//                .load()
//        } else {
//            Toast.makeText(requireContext(), "Book is not downloaded", Toast.LENGTH_SHORT).show()
//        }

//        PdfViewer.Builder(binding.root)
//            .build()
//            .load(file)
//        binding.btnBack.setOnClickListener {
//            findNavController().navigateUp()
//        }
    }
/*
    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
    }

    override fun onPageError(page: Int, t: Throwable?) {
        log("Cannot load page = $page")
    }

    override fun loadComplete(nbPages: Int) {
        printBookmarksTree(binding.pdfView.tableOfContents, "-")
    }

    private fun printBookmarksTree(
        tree: List<com.shockwave.pdfium.PdfDocument.Bookmark>,
        sep: String
    ) {
        for (b in tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.children, "$sep-")
            }
        }
    }*/
}