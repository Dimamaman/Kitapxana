package uz.gita.dima.kitapxana.presenter.homeadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.databinding.ItemBookBinding
import javax.inject.Inject


class HomeAdapter @Inject constructor() : ListAdapter<BookData, HomeAdapter.ImageViewHolder>(DIFF_CALL_BACK) {

    private var clickBook: ((BookData) -> Unit)? = null
    fun setClickBook(l: (BookData) -> Unit) {
        clickBook = l
    }

    inner class ImageViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookData) {
            binding.apply {
                textBookName.text = book.bookName
                textAuthor.text = book.author
                startSize.text = book.startSize
                Glide.with(itemView.context).load(book.imageUrl).centerCrop().into(binding.bookImg)
            }
            binding.bookImg.setOnClickListener {
                clickBook?.invoke(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    var deleteListener: ((BookData) -> Unit)? = null

    fun deleteListenerFun(block: (BookData) -> Unit) {
        deleteListener = block
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<BookData>() {
            override fun areItemsTheSame(oldItem: BookData, newItem: BookData): Boolean {
                return oldItem.bookName == newItem.bookName

            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: BookData, newItem: BookData): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }
        }
    }
}