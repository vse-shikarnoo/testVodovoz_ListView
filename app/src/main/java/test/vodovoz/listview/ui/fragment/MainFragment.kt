package test.vodovoz.listview.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
            Log.d("TAG", "observe categories: $it")
            categoriesListAdapter.submitList(it)
            viewModel.setCurrentCategory(it.first())
        }
        viewModel.currentCategory.observe(viewLifecycleOwner) {
            viewModel.getProductsByCategory()
            binding.categoryTv.text = it.name
            categoriesListAdapter.notifyDataSetChanged()
        }
        viewModel.error.observe(viewLifecycleOwner) {
            bindErrorState(true)
        }
        viewModel.products.observe(viewLifecycleOwner) {
            Log.d("TAG", "observe products: $it")
            productsListAdapter.submitList(it)
            bindErrorState(false)
            binding.productListRv.scrollToPosition(0)
        }
    }

    private fun initAdapters() {
        productsListAdapter = ProductsListAdapter()

        with(binding.productListRv) {
            adapter = productsListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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

    }

    private fun bindErrorState(flag: Boolean) {
        if (flag) {

        } else {

        }
    }
}