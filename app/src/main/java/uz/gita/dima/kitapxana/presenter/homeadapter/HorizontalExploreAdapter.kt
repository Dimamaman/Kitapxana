package uz.gita.dima.kitapxana.presenter.homeadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.databinding.ItemBookOneBinding

class HorizontalExploreAdapter : Adapter<HorizontalExploreAdapter.ItemHolder>() {

    private var list: List<BookData> = ArrayList()

    fun setData(l: List<BookData>) {
        list = l
        notifyDataSetChanged()
    }

    private var clickListener: ((BookData) -> Unit)? = null

    fun setClickListener(l: (BookData) -> Unit) {
        clickListener = l
    }

    inner class ItemHolder(private val binding: ItemBookOneBinding) :
        ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickListener?.invoke(list[bindingAdapterPosition])
            }
        }

        fun bind() {
            binding.apply {
                txtTitle.text = list[bindingAdapterPosition].bookName
                Glide.with(binding.root.context).load(list[bindingAdapterPosition].imageUrl)
                    .into(imgIcon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemBookOneBinding.inflate((
                LayoutInflater.from(parent.context)),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind()
    }
}