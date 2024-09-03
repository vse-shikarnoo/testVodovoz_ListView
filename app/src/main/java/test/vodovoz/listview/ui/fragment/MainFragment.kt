package test.vodovoz.listview.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import test.vodovoz.listview.R
import test.vodovoz.listview.databinding.FragmentMainBinding
import test.vodovoz.listview.ui.adapter.CategoriesListAdapter
import test.vodovoz.listview.ui.adapter.ProductsListAdapter
import test.vodovoz.listview.utils.autoCleared
import test.vodovoz.listview.vm.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()
    private var productsListAdapter: ProductsListAdapter by autoCleared()
    private var categoriesListAdapter: CategoriesListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapters()
        observe()
        listeners()

        viewModel.getCategories()
    }

    private fun observe() {
        viewModel.categories.observe(viewLifecycleOwner) {
            bindErrorState(false)
            categoriesListAdapter.submitList(it)
            viewModel.setCurrentCategory(it.first())
        }
        viewModel.currentCategory.observe(viewLifecycleOwner) {
            viewModel.getProductsByCategory()
            categoriesListAdapter.notifyDataSetChanged()
        }
        viewModel.error.observe(viewLifecycleOwner) {
            binding.loadingLayout.progressBar.visibility = View.GONE
            bindErrorState(true)
        }
        viewModel.products.observe(viewLifecycleOwner) {
            productsListAdapter.submitList(it)
            binding.productListRv.scrollToPosition(0)
        }
    }

    private fun initAdapters() {
        productsListAdapter = ProductsListAdapter()

        with(binding.productListRv) {
            adapter = productsListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val itemDecorator =
                DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
            itemDecorator.setDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.custom_horizontal_divider
                )!!
            )
            addItemDecoration(itemDecorator)
        }

        categoriesListAdapter = CategoriesListAdapter {
            viewModel.setCurrentCategory(categoriesListAdapter.currentList[it])
        }

        with(binding.categoryListRv) {
            adapter = categoriesListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        }
    }

    private fun listeners() {
        with(binding) {
            loadingLayout.retryBtn.setOnClickListener {
                binding.loadingLayout.progressBar.visibility = View.VISIBLE
                viewModel.getCategories()
            }
        }
    }

    private fun bindErrorState(flag: Boolean) {
        with(binding) {
            if (flag) {
                loadingLayout.errorTv.visibility = View.VISIBLE
                loadingLayout.retryBtn.visibility = View.VISIBLE


                categoryListRv.visibility = View.GONE
                productListRv.visibility = View.GONE
            } else {
                loadingLayout.errorTv.visibility = View.GONE
                loadingLayout.retryBtn.visibility = View.GONE

                loadingLayout.progressBar.visibility = View.VISIBLE
                categoryListRv.visibility = View.VISIBLE
                productListRv.visibility = View.VISIBLE
            }
            loadingLayout.progressBar.visibility = View.GONE
        }
    }
}