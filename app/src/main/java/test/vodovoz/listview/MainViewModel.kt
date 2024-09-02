package test.vodovoz.listview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    val repository = MainRepository()

    private val categoriesLD = MutableLiveData<List<Category>>()
    private val currentCategoryLD = MutableLiveData<Category>()
    private val productsLD = MutableLiveData<List<Product>>()

    val categories: LiveData<List<Category>>
        get() = categoriesLD
    val currentCategory: LiveData<Category>
        get() = currentCategoryLD
    val products: LiveData<List<Product>>
        get() = productsLD


    fun getCategories() {
        viewModelScope.launch {
            try {
                val result = repository.getCategories()
                categoriesLD.postValue(result)
                currentCategoryLD.postValue(result.first())

                Log.d(TAG, "getCategories: ${repository.getCategories()}")
            } catch (t: Throwable) {
                Log.e(TAG, "getCategories: ", t)
            }
        }
    }

    fun getProductsByCategory() {
        Log.d(TAG, "getProductsByCategory: category = ${currentCategory.value?.name}")
        viewModelScope.launch {
            try {
                productsLD.postValue(repository.getProductsByCategory(currentCategory.value!!))
                Log.d(TAG, "getProductsByCategory: ${repository.getProductsByCategory(
                    currentCategory.value!!
                )}")
            } catch (t: Throwable) {
                Log.e(TAG, "getProductsByCategory: ", t)
            }
        }
    }
}