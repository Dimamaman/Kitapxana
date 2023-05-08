package uz.gita.dima.kitapxana.presenter.homeadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.dima.kitapxana.data.BookData
import uz.gita.dima.kitapxana.data.CategoryData
import uz.gita.dima.kitapxana.databinding.VerticalItemBinding
import javax.inject.Inject

class ExploreAdapter @Inject constructor() : Adapter<ExploreAdapter.ItemHolder>() {

    private var list: List<CategoryData> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(l: List<CategoryData>) {
        list = l
        notifyDataSetChanged()
    }

    private var clickListener: ((BookData) -> Unit)? = null

    fun setClickListener(l: (BookData) -> Unit) {
        clickListener = l
    }

    inner class ItemHolder(private val binding: VerticalItemBinding) :
        ViewHolder(binding.root) {

        private val innerAdapter = HorizontalExploreAdapter()

        fun bind(data: CategoryData) {
            binding.horizontalRv.layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

                binding.category.text = data.title

            binding.horizontalRv.adapter = innerAdapter
              innerAdapter.setData(data.books)
                innerAdapter.setClickListener {
                    clickListener?.invoke(it)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            VerticalItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(list[position])
    }
}