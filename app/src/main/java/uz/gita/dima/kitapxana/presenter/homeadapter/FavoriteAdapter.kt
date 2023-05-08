package uz.gita.dima.kitapxana.presenter.homeadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.databinding.ItemSavedBookBinding
import javax.inject.Inject

class FavoriteAdapter @Inject constructor() :
    ListAdapter<BookData, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALL_BACK) {

    private var clickListener: ((BookData) -> Unit)? = null
    private var deleteClickListener: ((BookData) -> Unit)? = null

    fun setClickListener(l: (BookData) -> Unit) {
        clickListener = l
    }

    fun setDeleteClickListener(l: (BookData) -> Unit) {
        deleteClickListener = l
    }


    inner class FavoriteViewHolder(private val binding: ItemSavedBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookData: BookData) {
            binding.apply {
                txtTitle.text = bookData.bookName
                Glide.with(itemView.context).load(bookData.imageUrl).into(imgIcon)
            }
            binding.root.setOnClickListener {
                clickListener?.invoke(bookData)
            }

            binding.btnDelete.setOnClickListener {
                deleteClickListener?.invoke(bookData)
            }
        }
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<BookData>() {
            override fun areItemsTheSame(oldItem: BookData, newItem: BookData): Boolean {
                return oldItem.bookName == newItem.bookName
            }

            override fun areContentsTheSame(oldItem: BookData, newItem: BookData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(
            ItemSavedBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}