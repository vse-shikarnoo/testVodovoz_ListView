package test.vodovoz.listview.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.vodovoz.listview.model.Category
import test.vodovoz.listview.repository.MainRepository
import test.vodovoz.listview.model.Product

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    private val categoriesLD = MutableLiveData<List<Category>>()
    private val currentCategoryLD = MutableLiveData<Category>()
    private val productsLD = MutableLiveData<List<Product>>()
    private val errorLD = MutableLiveData<Unit>()

    val categories: LiveData<List<Category>>
        get() = categoriesLD
    val currentCategory: LiveData<Category>
        get() = currentCategoryLD
    val products: LiveData<List<Product>>
        get() = productsLD
    val error: LiveData<Unit>
        get() = errorLD


    fun getCategories() {
        viewModelScope.launch {
            try {
                val result = repository.getCategories()
                categoriesLD.postValue(result)

                Log.d(TAG, "getCategories: ${repository.getCategories()}")
            } catch (t: Throwable) {
                errorLD.postValue(Unit)
                Log.e(TAG, "getCategories: ", t)
            }
        }
    }

    fun getProductsByCategory() {
        Log.d(TAG, "getProductsByCategory: category = ${currentCategory.value?.name}")
        viewModelScope.launch {
            try {
                productsLD.postValue(repository.getProductsByCategory(currentCategory.value!!))
                Log.d(
                    TAG, "getProductsByCategory: ${
                        repository.getProductsByCategory(
                            currentCategory.value!!
                        )
                    }"
                )
            } catch (t: Throwable) {
                errorLD.postValue(Unit)
                Log.e(TAG, "getProductsByCategory: ", t)
            }
        }
    }

    fun setCurrentCategory(category: Category){
        categories.value?.forEach {
            if (it.name == category.name){
                currentCategoryLD.postValue(it)
                it.isCurrent = true
            }else{
                it.isCurrent = false
            }
        }
    }
}