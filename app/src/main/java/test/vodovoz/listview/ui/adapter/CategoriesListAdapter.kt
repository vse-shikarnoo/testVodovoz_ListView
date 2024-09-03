package test.vodovoz.listview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.vodovoz.listview.databinding.ItemCategoryListBinding
import test.vodovoz.listview.model.Category

class CategoriesListAdapter(
    private val onItemClick: (position: Int) -> Unit
) :
    ListAdapter<Category, CategoriesListAdapter.Holder>(CategoriesDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemCategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoriesDiffUtilCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemCategoryListBinding,
        private val onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(category: Category) {
            with(binding) {
                nameTv.text = category.name
                nameTv.textSize = if (category.isCurrent){
                    32f
                }else{
                    16f
                }
            }
        }
    }
}
