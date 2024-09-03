package test.vodovoz.listview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import test.vodovoz.listview.R
import test.vodovoz.listview.databinding.ItemProductListBinding
import test.vodovoz.listview.model.Product

class ProductsListAdapter :
    ListAdapter<Product, ProductsListAdapter.Holder>(ProductsDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductsDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(private val binding: ItemProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(product: Product){
                with(binding){
                    priceTv.text = root.resources.getString(
                        R.string.product_price,
                        product.prices.first().price.toString()
                    )
                    val imagePath = root.context.getString(R.string.picture_base_url)+product.picture
                    Glide.with(root)
                        .load(imagePath)
                        .into(pictureIv)
                }
            }
    }
}
